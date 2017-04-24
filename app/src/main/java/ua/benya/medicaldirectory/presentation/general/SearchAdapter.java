package ua.benya.medicaldirectory.presentation.general;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.search.SearchObj;

/**
 * Created by Shipohvost on 21.11.2016.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {
    private final List<SearchObj> mItems;

    private final SearchAdapter.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SearchObj item = (SearchObj) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };

    public SearchAdapter(@NonNull SearchAdapter.OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<SearchObj> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }



    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SearchHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        SearchObj child = mItems.get(position);
        holder.bind(child);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(child);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull SearchObj item);

    }

}
