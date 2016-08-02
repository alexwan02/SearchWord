package org.alexwan.searchword.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import org.alexwan.searchword.model.Response;
import org.alexwan.searchword.model.SearchModel;
import org.alexwan.searchword.model.SentenceModel;
import org.alexwan.searchword.model.WordModel;
import org.alexwan.searchword.net.RequestService;
import org.alexwan.searchword.net.RetrofitClient;
import org.alexwan.searchword.realm.RealmInstance;
import org.alexwan.searchword.ui.adapter.SearchAdapter;
import org.alexwan.searchword.ui.base.BasePresenterActivity;
import org.alexwan.searchword.ui.view.WordMainView;
import org.alexwan.searchword.ui.widget.searchview.SearchView;
import org.alexwan.searchword.utils.Constant;
import org.alexwan.searchword.utils.LogUtils;

import java.util.Locale;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class WordMainActivity extends BasePresenterActivity<WordMainView> implements SearchView.OnQueryTextListener,
        SearchAdapter.OnItemClickListener  , SearchAdapter.OnClickListener , TextView.OnEditorActionListener{
    private static final String TAG = WordMainActivity.class.getSimpleName();
    private Realm realm;
    private SearchAdapter mSearchAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = RealmInstance.getRealm();
        mSearchAdapter = new SearchAdapter(this, null, true);
        mViewClass.addSearchAdapter(mSearchAdapter);
        mSearchAdapter.setItemClickListener(this);
        mSearchAdapter.setOnClickListener(this);
        mViewClass.addQueryTextListener(this);
        mViewClass.addEditTextListener(this);
        addTextChangeEvent();
        getLastWord();
        RetrofitClient.getService(Constant.ICIBA_URL , RequestService.class)
                .getSentence("" , "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SentenceModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SentenceModel model) {
                        String string = model.getTts() + " content : " +
                        model.getContent() + " note : " +
                        model.getNote() + " image addr : " +
                        model.getPicture2();
                        LogUtils.i(TAG , "SentenceModel -> sid : " + model.getSid() + string);
                        mViewClass.setSentenceImage(model);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryHistory();
    }

    private void addTextChangeEvent() {
        Subscription viewSubscription =
                mViewClass.getEditTextChangeObservable()
                .flatMap(new Func1<TextViewTextChangeEvent, Observable<RealmResults<SearchModel>>>() {
                    @Override
                    public Observable<RealmResults<SearchModel>> call(TextViewTextChangeEvent event) {
                        String key = event.text().toString();
                        RealmQuery<SearchModel> query = realm.where(SearchModel.class)
                                .equalTo("type", SearchModel.SearchType.HISTORY.getValue());
                        if (!TextUtils.isEmpty(key)) {
                            return query.contains("content", key.trim(), Case.INSENSITIVE)
                                    .findAllSortedAsync("key" , Sort.DESCENDING)
                                    .asObservable();
                        } else {
                            return query.findAllSortedAsync("key" , Sort.DESCENDING)
                                    .asObservable();
                        }
                    }
                })
                .filter(new Func1<RealmResults<SearchModel>, Boolean>() {
                    @Override
                    public Boolean call(RealmResults<SearchModel> searchs) {
                        // Only continue once data is actually loaded
                        // RealmObservables will emit the unloaded (empty) list as it's first item
                        return searchs.isLoaded();
                    }
                })
                .subscribe(new Action1<RealmResults<SearchModel>>() {
                    @Override
                    public void call(RealmResults<SearchModel> searchModels) {
                        mSearchAdapter.updateData(searchModels);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });


        attachSubscription(viewSubscription);
    }

    private void queryHistory() {
        Subscription subscription =
                realm.where(SearchModel.class)
                    .contains("content", "", Case.INSENSITIVE)
                    .equalTo("type", SearchModel.SearchType.HISTORY.getValue())
                    .findAllSortedAsync("key" , Sort.DESCENDING)
                    .asObservable()
                    .filter(new Func1<RealmResults<SearchModel>, Boolean>() {
                        @Override
                        public Boolean call(RealmResults<SearchModel> searchModels) {
                            return searchModels.isLoaded();
                        }
                    })
                    .subscribe(new Action1<RealmResults<SearchModel>>() {
                        @Override
                        public void call(RealmResults<SearchModel> searchModels) {
                            mSearchAdapter.updateData(searchModels);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    });
        attachSubscription(subscription);
    }

    @Override
    protected Class<WordMainView> getViewClass() {
        return WordMainView.class;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(final String query) {
        return true;
    }

    private void saveQueryHistory(final String query) {
        final SearchModel model = new SearchModel();
        model.setType(SearchModel.SearchType.HISTORY);
        model.setContent(query.toLowerCase(Locale.getDefault()));

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(model);
            }
        });
    }

    private void queryWord(String word) {
        if (TextUtils.isEmpty(word)) {
            return;
        }
        mViewClass.setProgressBarVisible(true);
        Subscription subscription =
                RetrofitClient.getService(Constant.BASE_URL, RequestService.class)
                .getWord(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<WordModel>>() {
                    @Override
                    public void call(Response<WordModel> wordModel) {
                        saveWord(wordModel);
                        mViewClass.setProgressBarVisible(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        // when net work failed load local words
                        mViewClass.setProgressBarVisible(false);
                    }
                });
        attachSubscription(subscription);
    }

    private void saveWord(Response<WordModel> response) {
        if (response.getStatusCode() == 0) {
            final WordModel word = response.getData();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(word);
                }
            });
//            mViewClass.updateWordView(word);
        }
    }

    private void getLastWord(){
        Subscription subscription =
                realm.where(WordModel.class)
                        .findAllSortedAsync("date")
                        .asObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter(new Func1<RealmResults<WordModel>, Boolean>() {
                            @Override
                            public Boolean call(RealmResults<WordModel> wordModels) {
                                return wordModels.isLoaded();
                            }
                        })
                        .subscribe(new Action1<RealmResults<WordModel>>() {
                            @Override
                            public void call(RealmResults<WordModel> words) {
                                mViewClass.updateWordView(words.last());
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });
        mCompositeSubscription.add(subscription);
    }
    @Override
    public void onItemClick(View v, int position) {
        SearchModel model = mSearchAdapter.getData().get(position);
        String word = model.getContent();
        queryWord(word);
        mViewClass.closeSearchView();
    }

    @Override
    public void onPositionClick(final int position, final SearchModel model) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mSearchAdapter.getData().deleteFromRealm(position);
            }
        });
    }

    /**
     * EditView
     * @param v TextView
     * @param actionId actionId
     * @param event event
     * @return return true implicate handle by current manually
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        CharSequence query = v.getText();
        if(!TextUtils.isEmpty(query)){
            saveQueryHistory(query.toString());
            queryWord(query.toString());
            mViewClass.closeSearchView();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == SearchView.SPEECH_REQUEST_CODE){

            }
        }
    }
}
