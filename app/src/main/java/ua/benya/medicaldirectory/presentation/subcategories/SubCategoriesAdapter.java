package ua.benya.medicaldirectory.presentation.subcategories;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.drugs.categories.SubChild;

/**
 * Created by Shipohvost on 16.11.2016.
 */

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesHolder> {
    private final List<SubChild> mItems;

    private final SubCategoriesAdapter.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SubChild item = (SubChild) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };

    public SubCategoriesAdapter(@NonNull SubCategoriesAdapter.OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<SubChild> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }



    @Override
    public SubCategoriesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SubCategoriesHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(SubCategoriesHolder holder, int position) {
        SubChild child = mItems.get(position);
        holder.bind(child);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(child);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull SubChild item);

    }

}
