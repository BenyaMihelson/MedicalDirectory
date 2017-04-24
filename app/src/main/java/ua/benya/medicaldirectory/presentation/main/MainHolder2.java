package ua.benya.medicaldirectory.presentation.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.documents.categories.DocsCategories;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class MainHolder2 extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle)
    TextView mTiltel;

    private MainHolder2(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @NonNull
    public static MainHolder2 create(@NonNull Context context/*, int imageHeight, int imageWidth*/){
        View view = View.inflate(context, R.layout.list_item, null);
        // ImageView imageView = (ImageView) view.findViewById(R.id.image);
        //  ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        /*layoutParams.height = imageHeight;
        layoutParams.width = imageWidth;
        */
        //imageView.requestLayout();
        return new MainHolder2(view);


    }
    public void bind(DocsCategories datum){


        mTiltel.setText(datum.getTitle());
    }

}
