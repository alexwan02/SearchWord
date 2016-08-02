package org.alexwan.searchword.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import org.alexwan.searchword.R;
import org.alexwan.searchword.model.SentenceModel;
import org.alexwan.searchword.model.WordModel;
import org.alexwan.searchword.model.WordPronunciation;
import org.alexwan.searchword.ui.adapter.SearchAdapter;
import org.alexwan.searchword.ui.base.BasePresenterActivity;
import org.alexwan.searchword.ui.base.IBaseView;
import org.alexwan.searchword.ui.widget.progress.MaterialProgressBar;
import org.alexwan.searchword.ui.widget.searchview.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * WordMainView
 * Created by alexwan on 16/6/14.
 */
public class WordMainView implements IBaseView {
    private static final String TAG = WordMainView.class.getSimpleName();
    private View mView;
    private BasePresenterActivity mContext;

    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.word_content)
    TextView mWordContent;
    @BindView(R.id.word_us_pronunciation)
    TextView mWordPronunciationUs;
    @BindView(R.id.word_uk_pronunciation)
    TextView mWordPronunciationUk;
    @BindView(R.id.word_cn_definition)
    TextView mWordDefinition;
    @BindView(R.id.progress_bar)
    MaterialProgressBar mMaterialProgressBar;
    @BindView(R.id.sentence_img)
    SimpleDraweeView mSentenceImage;
    @Override
    public void onInit(Context context, LayoutInflater inflater, ViewGroup viewGroup) {
        this.mContext = (BasePresenterActivity) context;
        mView = inflater.inflate(R.layout.activity_word_main, viewGroup , false);
        ButterKnife.bind(this, mView);

    }

    @Override
    public View getView() {
        if(mView == null){
            throw new RuntimeException("View Should Be Init First.");
        }
        return mView;
    }

    @Override
    public void onBindView() {
        mSearchView.setVoice(true , mContext);
        mSearchView.setOnMenuClickListener(new SearchView.OnMenuClickListener() {
            @Override
            public void onMenuClick() {
                mSearchView.open(true);
            }
        });
    }

    @Override
    public void onDestroyView() {

    }

    public void addQueryTextListener(SearchView.OnQueryTextListener listener) {
        mSearchView.setOnQueryTextListener(listener);
    }

    public void addEditTextListener(TextView.OnEditorActionListener listener){
        mSearchView.setEditActionListener(listener);
    }
    public void closeSearchView() {
        mSearchView.close(true);
    }

    public void addSearchAdapter(SearchAdapter adapter) {
        mSearchView.setAdapter(adapter);
    }

    public Observable<TextViewTextChangeEvent> getEditTextChangeObservable(){
        return mSearchView.getEditTextChangeObservable();
    }

    public void updateWordView(WordModel word){
        mWordContent.setText(word.getContent());
        WordPronunciation pron = word.getPronunciations();
        mWordPronunciationUs.setText(mContext.getString(R.string.string_word_pronunciation , pron.getUk()));
        mWordPronunciationUk.setText(mContext.getString(R.string.string_word_pronunciation , pron.getUs()));
        mWordDefinition.setText(word.getDefinition());
    }

    public void setProgressBarVisible(boolean isVisible){
        mMaterialProgressBar.setVisibility(isVisible  ? View.VISIBLE : View.GONE);
    }

    public void setSentenceImage(SentenceModel mode){
        mSentenceImage.setImageURI(UriUtil.parseUriOrNull(mode.getPicture2()));
    }
}
