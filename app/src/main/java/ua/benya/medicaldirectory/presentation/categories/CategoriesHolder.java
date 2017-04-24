package ua.benya.medicaldirectory.presentation.categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.Child;

/**
 * Created by Shipohvost on 15.11.2016.
 */

public class CategoriesHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvTitle)
    TextView mTiltel;

    public CategoriesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static CategoriesHolder create(Context context){

        View view = View.inflate(context, R.layout.list_item, null);
        return new CategoriesHolder(view);

    }
    public void bind(Child datum){
        mTiltel.setText(datum.getTitle());
    }
}
