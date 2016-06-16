package org.alexwan.searchword.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * IBaseView
 * Created by alexwan on 16/6/14.
 */
public interface IBaseView {

    void onInit(Context context, LayoutInflater inflater, ViewGroup viewGroup);

    View getView();

    void onBindView();

    void onDestroyView();
}
