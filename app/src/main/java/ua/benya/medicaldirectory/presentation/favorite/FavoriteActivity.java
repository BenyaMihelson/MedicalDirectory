package ua.benya.medicaldirectory.presentation.favorite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.database.ApplicationDataBase;
import ua.benya.medicaldirectory.data.pojo.favorite.FavoriteObj;
import ua.benya.medicaldirectory.presentation.general.SearchAdapter;
import ua.benya.medicaldirectory.presentation.items.ItemDetailsActivity;

public class FavoriteActivity extends AppCompatActivity implements FavoriteAdapter.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mItemsRecycler;
   /* @BindView(R.id.recyclerViewS)
    RecyclerView mItemsRecyclerS;
*/
    @BindView(R.id.empty)
    View mEmptyView;

    private FavoriteAdapter mAdapter;
    private SearchAdapter mAdapterS;


    private ApplicationDataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("Избранное");

        int columns = getResources().getInteger(R.integer.columns_count);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        mItemsRecycler.setLayoutManager(layoutManager);
        mAdapter = createAdapter();

        mItemsRecycler.setAdapter(mAdapter);

        dataBase =  new ApplicationDataBase(this);

        showList(dataBase.getFavoriteList());


    }

    @NonNull
    private FavoriteAdapter createAdapter(){
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new FavoriteAdapter(this, this);
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull FavoriteObj item) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra("parentId", item.getId());
        startActivity(intent);

    }
    private void showList(List<FavoriteObj> items){
        mAdapter.changeDataSet(items);
        mItemsRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
