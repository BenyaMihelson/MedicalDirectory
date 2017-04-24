package ua.benya.medicaldirectory.presentation.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.database.ApplicationDataBase;
import ua.benya.medicaldirectory.data.pojo.UpdateTimeObj;
import ua.benya.medicaldirectory.data.pojo.documents.categories.DocsCategories;
import ua.benya.medicaldirectory.data.pojo.documents.categories.DocsCategoriesDatum;
import ua.benya.medicaldirectory.data.pojo.documents.items.DocsItems;
import ua.benya.medicaldirectory.data.pojo.documents.items.DocsItemsDatum;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.Child;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.Datum;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.RootCategory;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.SubChild;
import ua.benya.medicaldirectory.data.pojo.drugs.items.Item;
import ua.benya.medicaldirectory.data.pojo.drugs.items.ItemObj;
import ua.benya.medicaldirectory.data.pojo.search.SearchObj;
import ua.benya.medicaldirectory.helper.Constants;
import ua.benya.medicaldirectory.helper.Utils;
import ua.benya.medicaldirectory.network.ApiFactory;
import ua.benya.medicaldirectory.presentation.categories.ChildCategoriesActivity;
import ua.benya.medicaldirectory.presentation.documents.DocumentsActivity;
import ua.benya.medicaldirectory.presentation.documents.DocumentsItemsActivity;
import ua.benya.medicaldirectory.presentation.favorite.FavoriteActivity;
import ua.benya.medicaldirectory.presentation.general.SearchAdapter;
import ua.benya.medicaldirectory.presentation.items.ItemDetailsActivity;
import ua.benya.medicaldirectory.presentation.loading.LoadingView;

public class MainActivity extends AppCompatActivity implements MainAadapter.OnItemClickListener,
        MainAdapter2.OnItemClickListener, SearchAdapter.OnItemClickListener, SearchView.OnQueryTextListener
        /*SearchView.OnQueryTextListener*/ {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mItemsRecycler;
    @BindView(R.id.recyclerView2)
    RecyclerView mItemsRecycler2;

    @BindView(R.id.recyclerViewS)
    RecyclerView mItemsRecyclerS;


    @BindView(R.id.linear)
    RelativeLayout linearLayout;


    @BindView(R.id.empty)
    View mEmptyView;

    @BindView(R.id.btnToFav)
    LinearLayout btnToFav;

    private MainAadapter mAdapter;
    private MainAdapter2 mAdapter2;
    private SearchAdapter mAdapterS;

    private LoadingView mLoadingView;

    //private ItemService itemService;
    private ApiFactory factory;
    private ApplicationDataBase dataBase;

    private SharedPreferences sharedPreferences;

    private boolean isUpdated;

    ProgressDialog mProgressDialog;

    private int updateTime;



    private final String LOG_TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        setContentView(R.layout.loading_screen);

        //setViews();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        dataBase = new ApplicationDataBase(this);

        if (Utils.isConnected(getApplicationContext())) {
            Log.d(LOG_TAG, "internet ON");

            isApiUpdated();

        }else {
            Log.d(LOG_TAG, "internet OFF");

            setContentView(R.layout.activity_main);
            setViews();

            loadingDataFromDb();
        }
    }

    private void clearDbTables(){
        dataBase.clearTable(Constants.DATABASE.ROOT_CATEGORY_TABLE_NAME);
        dataBase.clearTable(Constants.DATABASE.CHILD_CATEGORY_TABLE_NAME);
        dataBase.clearTable(Constants.DATABASE.SUB_CHILD_CATEGORY_TABLE_NAME);
        dataBase.clearTable(Constants.DATABASE.ITEM_TABLE_NAME);
        dataBase.clearTable(Constants.DATABASE.DOCS_CATERIES_TABLE_NAME);
        dataBase.clearTable(Constants.DATABASE.DOCS_ITEMS_TABLE_NAME);

    }

    private void loadingDataFromDb(){
        showList(dataBase.getRootCategoryList());
        showList2(dataBase.getDocsCategoriesForMain());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);


    }


    private void setViews() {
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);


        int columns = getResources().getInteger(R.integer.columns_count);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getApplicationContext(), columns);
        RecyclerView.LayoutManager layoutManagerS = new GridLayoutManager(getApplicationContext(), columns);

        mItemsRecycler.setLayoutManager(layoutManager);
        mItemsRecycler2.setLayoutManager(layoutManager2);
        mItemsRecyclerS.setLayoutManager(layoutManagerS);

        mAdapter = createAdapter();
        mAdapter2 = createAdapter2();
        mAdapterS = createAdapterS();

        mItemsRecyclerS.setVisibility(View.INVISIBLE);

        mItemsRecycler.setAdapter(mAdapter);
        mItemsRecycler2.setAdapter(mAdapter2);
        mItemsRecyclerS.setAdapter(mAdapterS);

        btnToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intent);

            }
        });

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");


    }

    private void loadingDataFromApi() {

        factory = new ApiFactory();

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        Call<RootCategory> rootCategoryCall = factory.getItemService().getCategoryObj();


        rootCategoryCall.enqueue(new Callback<RootCategory>() {
            @Override
            public void onResponse(Call<RootCategory> call, Response<RootCategory> response) {

                RootCategory rootCategory = response.body();
                final List<Datum> datumList = rootCategory.getData();

                Log.d(LOG_TAG, datumList.size() + " size of Root Categories");
                dataBase.addRootCategory(datumList);
        }



            @Override
            public void onFailure(Call<RootCategory> call, Throwable t) {
                Log.d(LOG_TAG, t.getMessage() + " ERROR OF RETROFIT");

            }
        });
       // loadingView.hideLoadingIndicator();

        Call<ItemObj> itemObjCall = factory.getItemService().getItemObg();
        itemObjCall.enqueue(new Callback<ItemObj>() {

            @Override
            public void onResponse(Call<ItemObj> call,  Response<ItemObj> response) {

                ItemObj itemObj = response.body();
               final List<Item> itemList = itemObj.getData();

                dataBase.addItems(itemList);

                setContentView(R.layout.activity_main);
                setViews();

                SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("last_update", updateTime);
                editor.commit();




                loadingDataFromDb();

                /*showList(dataBase.getRootCategoryList());
                showList2(dataBase.getDocsCategoriesForMain());
*/



                //mProgressDialog.dismiss();
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            }

            @Override
            public void onFailure(Call<ItemObj> call, Throwable t) {
                showError();

            }
        });

        Call<DocsCategoriesDatum> categoriesDatumCall = factory.getItemService().getDocs();
        categoriesDatumCall.enqueue(new Callback<DocsCategoriesDatum>() {
            @Override
            public void onResponse(Call<DocsCategoriesDatum> call, Response<DocsCategoriesDatum> response) {
                DocsCategoriesDatum categoriesDatum = response.body();
                List<DocsCategories> categories = categoriesDatum.getData();
                for (int i = 0; i < categories.size(); i++) {

                    Log.d(LOG_TAG, categories.get(i).getTitle() + " DOCS CATEGORY");

                    dataBase.addDocsCaterories(categories.get(i));

                }
                //showList2(dataBase.getDocsCategoriesForMain());
            }

            @Override
            public void onFailure(Call<DocsCategoriesDatum> call, Throwable t) {

                Log.d(LOG_TAG, t.getMessage() + " ERROR OF RETROFIT");

            }
        });

        final Call<DocsItemsDatum> docsItemsDatumCall = factory.getItemService().getDocsItem();

        docsItemsDatumCall.enqueue(new Callback<DocsItemsDatum>() {
            @Override
            public void onResponse(Call<DocsItemsDatum> call, Response<DocsItemsDatum> response) {
                DocsItemsDatum itemsDatum = response.body();
                List<DocsItems> itemses = itemsDatum.getData();
                for (int i = 0; i < itemses.size(); i++) {

                    Log.d(LOG_TAG, itemses.get(i) + " DOCS ITEM");

                    dataBase.addDocsItems(itemses.get(i));

                }

            }

            @Override
            public void onFailure(Call<DocsItemsDatum> call, Throwable t) {

                Log.d(LOG_TAG, t.getMessage() + " ERROR OF RETROFIT");

            }
        });
       /* Call<UpdateTimeObj> call;
        call = factory.getItemService().getTime();

        call.enqueue(new Callback<UpdateTimeObj>() {
            @Override
            public void onResponse(Call<UpdateTimeObj> call, Response<UpdateTimeObj> response) {
                UpdateTimeObj obj = response.body();
                obj.getLastUpdate();
                Log.d(LOG_TAG, obj.getLastUpdate() + "LAST UPDATE");
                SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("last_update", Integer.parseInt(obj.getLastUpdate()));
                editor.commit();
            }

            @Override
            public void onFailure(Call<UpdateTimeObj> call, Throwable t) {

            }
        });
*/
    }

    private void showList(List<Datum> items) {

        mAdapter.changeDataSet(items);
        mItemsRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    private void showList2(List<DocsCategories> items) {

        mAdapter2.changeDataSet(items);
        mItemsRecycler2.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    private void showSearchList(List<SearchObj> list) {
        mAdapterS.changeDataSet(list);
        mItemsRecyclerS.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);

    }

    private void showError() {
//        mLoadingView.hideLoadingIndicator();
        mItemsRecycler.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }


    @NonNull
    private MainAadapter createAdapter() {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new MainAadapter(this);


    }

    @NonNull
    private MainAdapter2 createAdapter2() {
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new MainAdapter2(this);


    }

    @NonNull
    private SearchAdapter createAdapterS() {
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


    @Override
    public void onItemClick(@NonNull View view, @NonNull Datum item) {
        Intent intent = new Intent(this, ChildCategoriesActivity.class);
        intent.putExtra("parentId", item.getId());
        intent.putExtra("title", item.getTitle());
        startActivity(intent);

    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull DocsCategories item) {

        if(dataBase.getDocsChildCategories(item.getId()).size()>0){
            Intent intent = new Intent(this, DocumentsActivity.class);
            Log.d(LOG_TAG, item.getId() + " ID SELECT");
            intent.putExtra("parentId", item.getId());
            intent.putExtra("title", item.getTitle());
            startActivity(intent);

        }else{
            startActivity(new Intent(this, DocumentsItemsActivity.class).putExtra("parentId", item.getId()));
        }

    }

    @Override
    public void onItemClick(@NonNull View view, @NonNull SearchObj item) {
        if (item.getType().equals("DRUGS")) {

            Item item1 = dataBase.getItemObj(item.getId());


            Intent intent = new Intent(this, ItemDetailsActivity.class);
            intent.putExtra("item", item1);

            startActivity(intent);

        }
    }

    private void toFavorite(LinearLayout view){
        Intent intent = new Intent(this, FavoriteActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                linearLayout.setVisibility(View.VISIBLE);
                mItemsRecyclerS.setVisibility(View.INVISIBLE);
                showList(dataBase.getRootCategoryList());
                showList2(dataBase.getDocsCategoriesForMain());


                return false;
            }
        });
        searchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);

        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /*outState.putSerializable("list", (Serializable) mAdapter.getmItems());
        outState.putSerializable("list2", (Serializable) mAdapter2.getmItems());
*/
        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d(LOG_TAG, "onQueryTextSubmit ");

        return false;

    }


    @Override
    public boolean onQueryTextChange(String newText) {

        Log.d(LOG_TAG, "onQueryTextChange " + newText);
        linearLayout.setVisibility(View.INVISIBLE);
        mItemsRecyclerS.setVisibility(View.VISIBLE);

        Log.d(LOG_TAG, dataBase.getItemsBySearch(newText).size() + " SIZE OF SEARCH");

        showSearchList(dataBase.getItemsBySearch(newText));

        return false;
    }

    private boolean isApiUpdated() {

        SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
        final int myIntValue = sp.getInt("last_update", -1);

        factory = new ApiFactory();

        Log.d(LOG_TAG, myIntValue + " FROM PREF");

        Call<UpdateTimeObj> call;
        call = factory.getItemService().getTime();

        call.enqueue(new Callback<UpdateTimeObj>() {
            @Override
            public void onResponse(Call<UpdateTimeObj> call, Response<UpdateTimeObj> response) {
                UpdateTimeObj obj = response.body();
                updateTime = Integer.parseInt(obj.getLastUpdate());
                Log.d(LOG_TAG, obj.getLastUpdate() + " LAST UPDATE");
                if (Integer.parseInt(obj.getLastUpdate()) > myIntValue) {
                    isUpdated = true;
                    Log.d(LOG_TAG, isUpdated + "isUpdated");
                    clearDbTables();

                    SharedPreferences sp = getSharedPreferences("your_prefs", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("last_update", -1);
                    editor.commit();

                    loadingDataFromApi();


                } else {
                    Log.d(LOG_TAG, isUpdated + "LAST UPDATE");
                    isUpdated = false;
                    setContentView(R.layout.activity_main);
                    setViews();
                    loadingDataFromDb();

                }

            }

            @Override
            public void onFailure(Call<UpdateTimeObj> call, Throwable t) {

            }
        });

          return isUpdated;


    }

   }
