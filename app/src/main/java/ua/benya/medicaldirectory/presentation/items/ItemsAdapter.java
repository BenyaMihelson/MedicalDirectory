package ua.benya.medicaldirectory.presentation.items;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.drugs.items.Item;

/**
 * Created by Shipohvost on 16.11.2016.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsHolder> {
    private final List<Item> mItems;

    private final ItemsAdapter.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Item item = (Item) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };

    public ItemsAdapter(@NonNull ItemsAdapter.OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<Item> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }



    @Override
    public ItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ItemsHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(ItemsHolder holder, int position) {
        Item child = mItems.get(position);
        holder.bind(child);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(child);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull Item item);

    }
}
