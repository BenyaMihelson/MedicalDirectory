package ua.benya.medicaldirectory.presentation.documents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.documents.categories.Child;

/**
 * Created by Shipohvost on 29.11.2016.
 */

public class DocumentsListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle)
    TextView mTiltel;

    public DocumentsListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static DocumentsListHolder create(Context context){

        View view = View.inflate(context, R.layout.list_item, null);
        return new DocumentsListHolder(view);

    }
    public void bind(Child datum){
        mTiltel.setText(datum.getTitle());
    }
}
