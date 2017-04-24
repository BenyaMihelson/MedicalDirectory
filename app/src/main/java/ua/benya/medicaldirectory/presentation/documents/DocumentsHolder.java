package ua.benya.medicaldirectory.presentation.documents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.documents.items.DocsItems;

/**
 * Created by Shipohvost on 18.11.2016.
 */

public class DocumentsHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitle)
    TextView mTiltel;

    public DocumentsHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static DocumentsHolder create(Context context){

        View view = View.inflate(context, R.layout.list_item, null);
        return new DocumentsHolder(view);

    }
    public void bind(DocsItems datum){
        mTiltel.setText(datum.getTitle());
    }

}
