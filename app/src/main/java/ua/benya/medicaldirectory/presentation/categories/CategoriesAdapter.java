package ua.benya.medicaldirectory.presentation.categories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.drugs.categories.Child;

/**
 * Created by Shipohvost on 15.11.2016.
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesHolder> {
    private final List<Child> mItems;

    private final CategoriesAdapter.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Child item = (Child) view.getTag();
            mOnItemClickListener.onItemClick(view, item);

        }
    };

    public CategoriesAdapter(@NonNull CategoriesAdapter.OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<Child> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }



    @Override
    public CategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CategoriesHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(CategoriesHolder holder, int position) {
        Child child = mItems.get(position);
        holder.bind(child);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(child);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull Child item);

    }
}
