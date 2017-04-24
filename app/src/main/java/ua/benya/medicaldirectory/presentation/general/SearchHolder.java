package ua.benya.medicaldirectory.presentation.general;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.pojo.search.SearchObj;

import static android.R.attr.width;

/**
 * Created by Shipohvost on 21.11.2016.
 */

public class SearchHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tvTitle)
    TextView mTiltel;

    @BindView(R.id.cardView)
    CardView cardView;

    public SearchHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static SearchHolder create(Context context){

        View view = View.inflate(context, R.layout.list_item, null);
        return new SearchHolder(view);

    }
    public void bind(SearchObj datum){
        if(datum.isAnalog()){
           /* cardView.setLayoutParams((CardView.LayoutParams)new CardView.LayoutParams(
                    CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT));*/
/*            CardView.LayoutParams layoutParams = (CardView.LayoutParams) cardView.getLayoutParams();
            layoutParams.height = layoutParams.WRAP_CONTENT;
            cardView.setLayoutParams(layoutParams);
       */
            mTiltel.setText(datum.getTitle()+"\nАналоги:"+datum.getAnalogTxt());

        }else{
            mTiltel.setText(datum.getTitle());

        }

    }

}
