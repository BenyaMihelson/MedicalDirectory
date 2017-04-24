package ua.benya.medicaldirectory.presentation.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.drugs.items.Item;

/**
 * Created by Shipohvost on 16.11.2016.
 */

public class ItemsHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle)
    TextView mTiltel;

    public ItemsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static ItemsHolder create(Context context){

        View view = View.inflate(context, R.layout.list_item, null);
        return new ItemsHolder(view);

    }
    public void bind(Item datum){
        mTiltel.setText(datum.getTitle());
    }

}
