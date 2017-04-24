package ua.benya.medicaldirectory.presentation.favorite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.database.ApplicationDataBase;
import ua.benya.medicaldirectory.data.pojo.favorite.FavoriteObj;

/**
 * Created by Shipohvost on 22.11.2016.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteHolder> {

    Context mContext;


    private final List<FavoriteObj> mItems;

    private final FavoriteAdapter.OnItemClickListener mOnItemClickListener;

    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FavoriteObj item = (FavoriteObj) view.getTag();
            mOnItemClickListener.onItemClick(view, item);
        }
    };

    public FavoriteAdapter(@NonNull FavoriteAdapter.OnItemClickListener mOnItemClickListener, Context context) {
        this.mContext = context;
        this.mItems = new ArrayList<>();
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void changeDataSet(List<FavoriteObj> items){
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(int position){
        mItems.remove(position);
        notifyDataSetChanged();

    }



    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FavoriteHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(FavoriteHolder holder, final int position) {
        final FavoriteObj obj = mItems.get(position);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationDataBase dataBase = new ApplicationDataBase(mContext);
                dataBase.removeFromFavorite(obj.getId());
                removeItem(position);
            }
        });

        holder.bind(obj);
        holder.itemView.setOnClickListener(mInternalListener);
        holder.itemView.setTag(obj);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public interface OnItemClickListener {

        void onItemClick(@NonNull View view, @NonNull FavoriteObj item);

    }

}
