package ua.benya.medicaldirectory.presentation.items;

import android.app.SearchManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.database.ApplicationDataBase;
import ua.benya.medicaldirectory.data.pojo.drugs.items.Item;
import ua.benya.medicaldirectory.data.pojo.search.SearchObj;
import ua.benya.medicaldirectory.presentation.favorite.FavoriteActivity;
import ua.benya.medicaldirectory.presentation.general.SearchAdapter;

public class ItemsListActivity extends AppCompatActivity implements ItemsAdapter.OnItemClickListener,
SearchAdapter.OnItemClickListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mItemsRecycler;

    @BindView(R.id.empty)
    View mEmptyView;

    @BindView(R.id.btnToFav)
    LinearLayout btnToFav;


    private ItemsAdapter mAdapter;


    @BindView(R.id.recyclerViewS)
    RecyclerView mItemsRecyclerS;
    private SearchAdapter mAdapterS;


    //private LoadingView mLoadingView;

    //private ItemService itemService;
    //private ApiFactory factory;
    private ApplicationDataBase dataBase;

    private  int parentId;


    private final String LOG_TAG = "ItemsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_categories);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(mToolbar);

        btnToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ItemsListActivity.this, FavoriteActivity.class);
                startActivity(intent);

            }
        });

        int columns = getResources().getInteger(R.integer.columns_count);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        mItemsRecycler.setLayoutManager(layoutManager);
        mAdapter = createAdapter();
        mItemsRecycler.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getApplicationContext(), columns);
        mItemsRecyclerS.setLayoutManager(layoutManager2);
        mAdapterS = createAdapterS();
        mItemsRecyclerS.setAdapter(mAdapterS);

        dataBase =  new ApplicationDataBase(this);

        parentId = getIntent().getIntExtra("parentId", 0);

        Log.d(LOG_TAG, parentId + " parentId");

        List<Item> list = dataBase.getItemsList(parentId);

        showList(list);


    }
    @NonNull
    private ItemsAdapter createAdapter(){
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new ItemsAdapter(this);


    }
    @NonNull
    private SearchAdapter createAdapterS(){
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new SearchAdapter(this);


    }


    private void showList(List<Item> items){

        mAdapter.changeDataSet(items);
        mItemsRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
        for (int i = 0; i<items.size(); i++){
            Log.d(LOG_TAG, items.get(i).getParentId() + " PARENT ID OF DRUGS");
        }
    }

    private void showSearchList(List<SearchObj> list){
        mAdapterS.changeDataSet(list);
        mItemsRecyclerS.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);

    }


    private void showError(){
        mItemsRecycler.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
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

    @Override
    public void onItemClick(@NonNull View view, @NonNull Item item) {
        Intent intent = new Intent(this, ItemDetailsActivity.class);

        intent.putExtra("item", item);

        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(LOG_TAG, "onQueryTextChange " + newText);
                mItemsRecycler.setVisibility(View.INVISIBLE);
                mItemsRecyclerS.setVisibility(View.VISIBLE);

                Log.d(LOG_TAG, dataBase.getItemsBySearch(newText).size()  + " SIZE OF SEARCH");

                showSearchList(dataBase.getItemsBySearch(newText));


                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                mItemsRecycler.setVisibility(View.VISIBLE);
                mItemsRecyclerS.setVisibility(View.INVISIBLE);

                showList(dataBase.getItemsList(parentId));

                return false;
            }
        });
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        return true;
    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull SearchObj item) {
        if(item.getType().equals("DRUGS")){

            Item item1 = dataBase.getItemObj(item.getId());


            Intent intent = new Intent(this, ItemDetailsActivity.class);
            intent.putExtra("item", item1);

            startActivity(intent);

        }
    }



}
