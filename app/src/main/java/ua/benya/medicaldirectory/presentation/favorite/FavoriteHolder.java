package ua.benya.medicaldirectory.presentation.favorite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.favorite.FavoriteObj;

/**
 * Created by Shipohvost on 22.11.2016.
 */

public class FavoriteHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle)
    TextView mTiltel;

    @BindView(R.id.ic_delete)
    ImageView btnDelete;

    public FavoriteHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static FavoriteHolder create(Context context){

        View view = View.inflate(context, R.layout.favorite_list_item, null);
        return new FavoriteHolder(view);

    }
    public void bind(FavoriteObj datum){
        mTiltel.setText(datum.getTitle());
    }

}
