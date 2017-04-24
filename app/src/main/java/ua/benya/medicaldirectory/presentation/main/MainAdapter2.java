package ua.benya.medicaldirectory.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.documents.categories.DocsCategories;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class MainAdapter2 extends RecyclerView.Adapter<MainHolder2> {
    private final List<DocsCategories> mItems;

    private final MainAdapter2.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DocsCategories item = (DocsCategories) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };


    public MainAdapter2(@NonNull MainAdapter2.OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<DocsCategories> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MainHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return MainHolder2.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(MainHolder2 holder, int position) {
        DocsCategories datum = mItems.get(position);
        holder.bind(datum);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(datum);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull DocsCategories item);

    }
    public List<DocsCategories> getmItems(){

        return mItems;
    }
}
