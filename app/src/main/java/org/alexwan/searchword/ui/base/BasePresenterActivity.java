package org.alexwan.searchword.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * BasePresenterActivity
 * Created by alexwan on 16/6/14.
 */
public abstract class BasePresenterActivity<V extends IBaseView> extends AppCompatActivity{
    protected CompositeSubscription mCompositeSubscription;
    protected V mViewClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mViewClass = getViewClass().newInstance();
            mViewClass.onInit(this , getLayoutInflater() , null);
            setContentView(mViewClass.getView());
            mCompositeSubscription = new CompositeSubscription();
            Fresco.initialize(this);
            mViewClass.onBindView();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mViewClass.onDestroyView();
        detachSubscription();
        super.onDestroy();
    }

    protected abstract Class<V> getViewClass();

    protected void attachSubscription(Subscription subscription){
        if(mCompositeSubscription != null){
            mCompositeSubscription.add(subscription);
        }
    }

    private void detachSubscription(){
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }
}
