package org.alexwan.searchword.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.alexwan.searchword.R;
import org.alexwan.searchword.ui.base.IBaseView;

/**
 * SearchAdapterView
 * Created by alexwan on 16/6/15.
 */
public class SearchAdapterView implements IBaseView {
    private View mView;
    private Context context;
    @Override
    public void onInit(Context context, LayoutInflater inflater, ViewGroup viewGroup) {
        mView =inflater.inflate(R.layout.search_item , viewGroup , false);
    }

    @Override
    public View getView() {
        if(mView == null){
            throw new IllegalArgumentException("View has't init , check view in onInit() method");
        }
        return mView;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void onDestroyView() {

    }

}
