package ua.benya.medicaldirectory.presentation.documents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.documents.categories.Child;

/**
 * Created by Shipohvost on 29.11.2016.
 */

public class DocumentsListAdapter extends RecyclerView.Adapter<DocumentsListHolder> {
    private final List<Child> mItems;

    private final DocumentsListAdapter.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Child item = (Child) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };

    public DocumentsListAdapter(@NonNull DocumentsListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<Child> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }



    @Override
    public DocumentsListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DocumentsListHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(DocumentsListHolder holder, int position) {
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
