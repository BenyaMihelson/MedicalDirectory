package ua.benya.medicaldirectory.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.drugs.categories.Datum;

/**
 * Created by Shipohvost on 11.11.2016.
 */

public class MainAadapter extends RecyclerView.Adapter<MainHolder> {
    private final List<Datum> mItems;

    private final OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Datum item = (Datum) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };


    public MainAadapter(@NonNull OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<Datum> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MainHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        Datum datum = mItems.get(position);
        holder.bind(datum);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(datum);


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull Datum item);

    }
    public List<Datum> getmItems(){

        return mItems;
    }
}
