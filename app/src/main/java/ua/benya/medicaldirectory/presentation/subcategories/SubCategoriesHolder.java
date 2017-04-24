package ua.benya.medicaldirectory.presentation.subcategories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.SubChild;

/**
 * Created by Shipohvost on 16.11.2016.
 */

public class SubCategoriesHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tvTitle)
    TextView mTiltel;

    public SubCategoriesHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static SubCategoriesHolder create(Context context){

        View view = View.inflate(context, R.layout.list_item, null);
        return new SubCategoriesHolder(view);

    }
    public void bind(SubChild datum){
        mTiltel.setText(datum.getTitle());
    }

}
