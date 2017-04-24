package ua.benya.medicaldirectory.presentation.items;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.database.ApplicationDataBase;
import ua.benya.medicaldirectory.data.pojo.drugs.items.Item;

public class ItemDetailsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.empty)
    View mEmptyView;



    @BindView(R.id.scrollView)
    NestedScrollView sv;
    @BindView(R.id.linearInScroll)
    LinearLayout linearLayout;
    @BindView(R.id.title)
    TextView tVtitle;

    @BindView(R.id.pharmacology)
    TextView tVpharmacology;
    @BindView(R.id.testimony)
    TextView tVtestimony;
    @BindView(R.id.contraindications)
    TextView tVcontraindications;
    @BindView(R.id.dosage)
    TextView tVdosage;
    @BindView(R.id.side_effect)
    TextView tVside_effect;
    @BindView(R.id.counterparts)
    TextView tVcounterparts;
    @BindView(R.id.mkb10)
    TextView tVmkb10;
    @BindView(R.id.additional)
    TextView tVadditional;

    /*@BindView(R.id.btnPharmacology)
    Button mBtnPharmacology;
    @BindView(R.id.btnTestimony)
    Button mBtnTestimony;
    @BindView(R.id.btnContraindications)
    Button mBtnContraindications;
    @BindView(R.id.btnDosage)
    Button mBtnDosage;
    @BindView(R.id.btnSide_effect)
    Button mBtnSide_effect;
    @BindView(R.id.btnCounterparts)
    Button mBtnCounterparts;
    @BindView(R.id.btnMkb10)
    Button mBtnbtnMkb10;
    @BindView(R.id.btnAdditional)
    Button mBtnAdditional;
*/
    private Item itemDrug;
    private boolean isFavorite = false;
    private ApplicationDataBase dataBase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        dataBase =  new ApplicationDataBase(this);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(mToolbar);

        itemDrug = (Item) getIntent().getSerializableExtra("item");

        if(itemDrug==null){
            int id = getIntent().getIntExtra("parentId", 0);
            itemDrug = dataBase.getItemObj(id);
        }

        tVtitle.setText(itemDrug.getTitle());

        tVpharmacology.setText(itemDrug.getPharmacology());
        tVtestimony.setText(itemDrug.getTestimony());
        tVcontraindications.setText(itemDrug.getContraindications());
        tVdosage.setText(itemDrug.getDosage());
        tVside_effect.setText(itemDrug.getSideEffect());
        tVcounterparts.setText(itemDrug.getCounterparts());
        tVmkb10.setText(itemDrug.getMkb10());
        tVadditional.setText(itemDrug.getAdditional());


        tVpharmacology.setTextSize(0);
        tVtestimony.setTextSize(0);
        tVcontraindications.setTextSize(0);
        tVdosage.setTextSize(0);
        tVside_effect.setTextSize(0);
        tVcounterparts.setTextSize(0);
        tVmkb10.setTextSize(0);
        tVadditional.setTextSize(0);

    }

    /*@OnClick({})
    public void OnClickSpoilerButtons(View b){
        switch (b.getId()){
            case (R.id.btnPharmacology):
                showHideText(mBtnPharmacology, tVpharmacology);
                break;
            case (R.id.btnTestimony):
                showHideText(mBtnTestimony, tVtestimony);
                break;
            case (R.id.btnContraindications):
                showHideText(mBtnContraindications, tVcontraindications);
                break;
            case (R.id.btnDosage):
                showHideText(mBtnDosage, tVdosage);
                break;
            case (R.id.btnSide_effect):
                showHideText(mBtnSide_effect, tVside_effect);
                break;
            case (R.id.btnCounterparts):
                showHideText(mBtnCounterparts, tVcounterparts);
                break;
            case (R.id.btnMkb10):
                showHideText(mBtnbtnMkb10, tVmkb10);
                break;
            case (R.id.btnAdditional):
                showHideText(mBtnAdditional, tVadditional);
                break;

        }

    }
*/
    @OnClick({})
    public void OnClickSpoiler(View b){
        switch (b.getId()){
            case (R.id.title_pharmacologyL):
                showHideText(/*mBtnPharmacology,*/ tVpharmacology);
                break;
            case (R.id.title_testimonyL):
                showHideText(/*mBtnTestimony, */tVtestimony);
                break;
            case (R.id.title_contraindicationsL):
                showHideText(/*mBtnContraindications, */tVcontraindications);
                break;
            case (R.id.title_dosageL):
                showHideText(/*mBtnDosage, */tVdosage);
                break;
            case (R.id.title_side_effectL):
                showHideText(/*mBtnSide_effect, */tVside_effect);
                break;
            case (R.id.title_counterpartsL):
                showHideText(/*mBtnCounterparts, */tVcounterparts);
                break;
            case (R.id.title_mkb10L):
                showHideText(/*mBtnbtnMkb10, */tVmkb10);
                break;
            case (R.id.title_additionalL):

                showHideText(/*mBtnAdditional, */tVadditional);

                //linearLayout.computeScroll();

                break;

        }

    }


    private void showHideText(/*Button btn,*/ TextView tv) {
        if(tv.getTextSize()>0){
            tv.setVisibility(View.INVISIBLE);
            tv.setTextSize(0);
           // btn.setText("+");
        }else{
            tv.setVisibility(View.VISIBLE);
            tv.setTextSize(15);
           /* sv.fullScroll(NestedScrollView.FOCUS_DOWN);
            sv.scrollTo(0,linearLayout.getBottom());
            linearLayout.scrollTo(0, linearLayout.getBottom());
           */// btn.setText("-");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_favorite:

                isFavorite = !isFavorite;
                if(isFavorite){
                    dataBase.addItemToFavorite(itemDrug);

                    item.setIcon(R.drawable.ic_action_favorite_on);

                }else{
                    dataBase.removeFromFavorite(itemDrug.getId());
                    item.setIcon(R.drawable.ic_action_favorite_off);
                }



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_details_menu, menu);
        if(dataBase.isInFavorite(itemDrug.getId())){
            menu.findItem(R.id.action_favorite).setIcon(R.drawable.ic_action_favorite_on);

            //menu.getItem(R.id.action_favorite).setIcon(R.drawable.ic_action_favorite_on);
            isFavorite = true;

        }else{

            menu.findItem(R.id.action_favorite).setIcon(R.drawable.ic_action_favorite_off);
            //menu.getItem(R.id.action_favorite).setIcon(R.drawable.ic_action_favorite_off);
            isFavorite = false;

        }

        //
        return super.onCreateOptionsMenu(menu);
    }
}
