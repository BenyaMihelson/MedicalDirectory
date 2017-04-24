package ua.benya.medicaldirectory.presentation.documents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.database.ApplicationDataBase;

public class DocumentsActivity extends AppCompatActivity implements DocumentsListAdapter.OnItemClickListener {
    private final String LOG_TAG = "DocumentsActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mItemsRecycler;

    @BindView(R.id.empty)
    View mEmptyView;

    private DocumentsListAdapter mAdapter;

    ProgressDialog mProgressDialog;

    //private LoadingView mLoadingView;

    //private ItemService itemService;
    //private ApiFactory factory;
    private ApplicationDataBase dataBase;

    private String downLoadedFileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_categories);
        ButterKnife.bind(this);
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(mToolbar);


        int columns = getResources().getInteger(R.integer.columns_count);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        mItemsRecycler.setLayoutManager(layoutManager);
        mAdapter = createAdapter();
        mItemsRecycler.setAdapter(mAdapter);

        dataBase =  new ApplicationDataBase(this);

        int parentId = getIntent().getIntExtra("parentId", 0);

        Log.d(LOG_TAG, parentId + " SELECTED ID RECEIVED");

        Log.d(LOG_TAG, dataBase.getDocsChildCategories(parentId).size() + " SIZE OF LIST");

        showList(dataBase.getDocsChildCategories(parentId));


    }

    private void showList(List<ua.benya.medicaldirectory.data.pojo.documents.categories.Child> items){

        mAdapter.changeDataSet(items);
        mItemsRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }
    private void showError(){
//        mLoadingView.hideLoadingIndicator();
        mItemsRecycler.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }


    @NonNull
    private DocumentsListAdapter createAdapter(){
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new DocumentsListAdapter(this);

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
    public void onItemClick(@NonNull View view, @NonNull
            ua.benya.medicaldirectory.data.pojo.documents.categories.Child item) {
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

}
