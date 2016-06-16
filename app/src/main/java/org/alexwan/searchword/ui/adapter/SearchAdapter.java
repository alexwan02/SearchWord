package org.alexwan.searchword.ui.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.alexwan.searchword.R;
import org.alexwan.searchword.model.SearchModel;
import org.alexwan.searchword.ui.base.BaseRealmPresentAdapter;
import org.alexwan.searchword.ui.view.SearchAdapterView;
import org.alexwan.searchword.ui.widget.searchview.SearchView;
import org.alexwan.searchword.utils.LogUtils;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;

/**
 * SearchAdapter
 * Created by alexwan on 16/6/15.
 */
public class SearchAdapter extends BaseRealmPresentAdapter<SearchAdapterView, SearchModel, SearchAdapter.SearchViewHolder> {
    private static final String TAG = SearchAdapter.class.getSimpleName();
//    private String key = "   ";
    private OnItemClickListener mItemClickListener;
    private OnClickListener mOnClickListener;

    public SearchAdapter(Context context, OrderedRealmCollection<SearchModel> items, boolean autoUpdate) {
        super(context, items, autoUpdate);
    }

    @Override
    protected SearchViewHolder createViewHolder(View view) {
        return new SearchViewHolder(view);
    }

    @Override
    protected void bindViewHolder(SearchViewHolder holder, int position, SearchAdapterView viewClass) {

        SearchModel item = getData().get(position);
        if (item.getType() == SearchModel.SearchType.HISTORY.getValue()) {
            holder.mIcon.setImageResource(R.drawable.search_ic_history_black_24dp);
        } else {
            holder.mIcon.setImageResource(R.drawable.search_ic_search_black_24dp);
        }
        holder.mIcon.setColorFilter(SearchView.getIconColor(), PorterDuff.Mode.SRC_IN);
        holder.mDeleteIcon.setImageResource(R.drawable.search_ic_clear_black_24dp);
        holder.mDeleteIcon.setColorFilter(SearchView.getIconColor(), PorterDuff.Mode.SRC_IN);
        holder.mTitle.setTextColor(SearchView.getTextColor());
        String title = item.getContent().toLowerCase(Locale.getDefault());
//        if (key.trim().contains(title)) {
//            holder.mTitle.setText(item.getContent());
//        } else {
            holder.mTitle.setText(item.getContent());
//        }
    }


    @Override
    protected Class<SearchAdapterView> getViewClass() {
        return SearchAdapterView.class;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_search_icon)
        ImageView mIcon;
        @BindView(R.id.item_search_title)
        TextView mTitle;
        @BindView(R.id.item_search_delete)
        ImageView mDeleteIcon;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            mDeleteIcon.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.item_search_delete:
                    if (mOnClickListener != null) {
                        int position = getLayoutPosition();
                        LogUtils.i(TAG , "DELETE -> position : " + position);
                        mOnClickListener.onPositionClick(position , getData().get(position));
                    }
                    break;
                default:
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getLayoutPosition());
                    }
                    break;
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnClickListener {
        void onPositionClick(int position, SearchModel model);
    }
}
