package org.alexwan.searchword.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.OrderedRealmCollection;
import io.realm.RealmObject;
import io.realm.RealmRecyclerViewAdapter;

/**
 * BasePresenterAdapter
 * Apply RealmRecyclerViewAdapter
 * Created by alexwan on 16/6/15.
 */
public abstract class BaseRealmPresentAdapter<V extends IBaseView, T extends RealmObject, VH extends RecyclerView.ViewHolder> extends RealmRecyclerViewAdapter<T, VH> {
    protected V mViewClass;

    public BaseRealmPresentAdapter(Context context, OrderedRealmCollection<T> data, boolean autoUpdate) {
        super(context, data, autoUpdate);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            mViewClass = getViewClass().newInstance();
            mViewClass.onInit(parent.getContext(), inflater, parent);
            return createViewHolder(mViewClass.getView());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        bindViewHolder(holder, position, mViewClass);
    }

    protected abstract Class<V> getViewClass();

    protected abstract VH createViewHolder(View view);

    protected abstract void bindViewHolder(VH holder, int position, V viewClass);
}
