package ua.benya.medicaldirectory.presentation.documents;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
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
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.benya.medicaldirectory.R;
import ua.benya.medicaldirectory.data.database.ApplicationDataBase;
import ua.benya.medicaldirectory.data.pojo.documents.items.DocsItems;
import ua.benya.medicaldirectory.helper.Constants;

public class DocumentsItemsActivity extends AppCompatActivity  implements DocumentsAdapter.OnItemClickListener{

    public static final String LOG_TAG = "DocumentsItemsActivity";

    // File intStorageDirectory = new File(this.getFilesDir()+"/");


    @BindView(R.id.toolbar)
Toolbar mToolbar;

    @BindView(R.id.recyclerView)
    RecyclerView mItemsRecycler;

    @BindView(R.id.empty)
    View mEmptyView;

    private DocumentsAdapter mAdapter;

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

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Загрузка документа...");
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);

        int columns = getResources().getInteger(R.integer.columns_count);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), columns);
        mItemsRecycler.setLayoutManager(layoutManager);
        mAdapter = createAdapter();
        mItemsRecycler.setAdapter(mAdapter);

        dataBase =  new ApplicationDataBase(this);

        int parentId = getIntent().getIntExtra("parentId", 0);

        Log.d(LOG_TAG, parentId + " SELECTED ID RECEIVED");

        Log.d(LOG_TAG, dataBase.getDocsChildCategories(parentId).size() + " SIZE OF LIST");

        showList(dataBase.getDocsItems(parentId));
    }
    @NonNull
    private DocumentsAdapter createAdapter(){
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.rows_count, typedValue, true);
        float rowsCount = typedValue.getFloat();
        int actionBarHeight = getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)
                ? TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics())
                : 0;
        int imageHeight = (int) ((getResources().getDisplayMetrics().heightPixels - actionBarHeight) / rowsCount);

        int columns = getResources().getInteger(R.integer.columns_count);
        int imageWidth = getResources().getDisplayMetrics().widthPixels / columns;

        return new DocumentsAdapter(this);

    }

    private void showList(List<DocsItems> items){

        mAdapter.changeDataSet(items);
        mItemsRecycler.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    void openFile(){
        File file = new File(Environment.getExternalStorageDirectory().toString()+"/testthreepdf/"+downLoadedFileName);
        MimeTypeMap map = MimeTypeMap.getSingleton();
        String ext = MimeTypeMap.getFileExtensionFromUrl(file.getName());
        String type = map.getMimeTypeFromExtension(ext);

        if (type == null)
            type = "*/*";

        try{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.fromFile(file);

            intent.setDataAndType(data, type);

            startActivity(intent);
        }catch (Exception e){

            Toast.makeText(this,"Не найдено приложение, которое бы могло открыть файл данного типа", Toast.LENGTH_LONG).show();

        }




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
    public void onItemClick(@NonNull View view, @NonNull DocsItems item) {
        String fileName = item.getFile();
        downLoadedFileName = fileName;
        final DownloadTask downloadTask = new DownloadTask(this);

        downloadTask.execute(Constants.HTTP.FILES_DOWLOADED_URL + fileName);
        Log.d(LOG_TAG, Constants.HTTP.FILES_DOWLOADED_URL + fileName + " RRRRRRRRRRRRRRRRRRRR");

        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                downloadTask.cancel(true);
            }
        });
    }


    private class DownloadTask extends AsyncTask<String, Integer, String> {
        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(LOG_TAG, result + " On result");

            mWakeLock.release();
            mProgressDialog.dismiss();
            if (result != null){
                Log.d(LOG_TAG, "Download error: "+result);
                Toast.makeText(context,"Download error: "+result, Toast.LENGTH_LONG).show();
            }

            else{
                Toast.makeText(context,"File downloaded", Toast.LENGTH_SHORT).show();

            }

            openFile();
        }






        @Override
        protected String doInBackground(String... sUrl) {
            Log.d(LOG_TAG, sUrl + " URL OF FILE");
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                //String intStorageDirectory = Environment.getDataDirectory().toString();
               // final File intStorageDirectory = new File();
                File folder = new File(extStorageDirectory, "testthreepdf");
                folder.mkdir();

                // download the file

                String fileName = sUrl[0].replace(Constants.HTTP.FILES_DOWLOADED_URL,"");
                input = connection.getInputStream();
                output = new FileOutputStream(folder + "/"+ fileName);

                Log.d(LOG_TAG, output.toString() + " path to file");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
    }
}
