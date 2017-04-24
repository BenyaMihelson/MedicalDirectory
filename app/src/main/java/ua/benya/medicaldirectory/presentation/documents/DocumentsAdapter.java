package ua.benya.medicaldirectory.presentation.documents;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.documents.items.DocsItems;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsHolder> {

    private final List<DocsItems> mItems;

    private final DocumentsAdapter.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DocsItems item = (DocsItems) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };

    public DocumentsAdapter(@NonNull DocumentsAdapter.OnItemClickListener mOnItemClickListener) {
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<DocsItems> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }



    @Override
    public DocumentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DocumentsHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(DocumentsHolder holder, int position) {
        DocsItems child = mItems.get(position);
        holder.bind(child);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(child);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull DocsItems item);

    }

}
