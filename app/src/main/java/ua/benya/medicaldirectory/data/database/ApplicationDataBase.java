package ua.benya.medicaldirectory.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ua.benya.medicaldirectory.data.pojo.documents.categories.DocsCategories;
import ua.benya.medicaldirectory.data.pojo.documents.items.DocsItems;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.Child;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.Datum;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.SubChild;
import ua.benya.medicaldirectory.data.pojo.drugs.items.Item;
import ua.benya.medicaldirectory.data.pojo.favorite.FavoriteObj;
import ua.benya.medicaldirectory.data.pojo.search.SearchObj;
import ua.benya.medicaldirectory.helper.Constants;

/**
 * Created by Shipohvost on 14.11.2016.
 */

public class ApplicationDataBase extends SQLiteOpenHelper {
    public static final String LOg_TAG = "Database";
    Cursor cursor;

    public ApplicationDataBase(Context context) {
        super(context, Constants.DATABASE.DB_NAME, null, Constants.DATABASE.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(Constants.DATABASE.CREATE_ROOT_CATEGORY_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_CHILD_CATEGORY_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_SUB_CHILD_CATEGORY_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_ITEM_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_DOCUMENTS_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_DOCUMENTS_ITEMS_TABLE_QUERY);
            db.execSQL(Constants.DATABASE.CREATE_FAVORITE_TABLE_QUERY);


        }catch (SQLException ex){

            Log.d(LOg_TAG, ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DATABASE.ROOT_CATEGORY_TABLE_DROP_QUERY);
        db.execSQL(Constants.DATABASE.CHILD_CATEGORY_TABLE_DROP_QUERY);
        db.execSQL(Constants.DATABASE.SUB_CHILD_CATEGORY_TABLE_DROP_QUERY);
        db.execSQL(Constants.DATABASE.ITEM_TABLE_DROP_QUERY);
        db.execSQL(Constants.DATABASE.DOCS_TABLE_DROP_QUERY);
        db.execSQL(Constants.DATABASE.DOCS_ITEM_TABLE_DROP_QUERY);
        db.execSQL(Constants.DATABASE.FAVORITE_TABLE_DROP_QUERY);

        this.onCreate(db);
    }

    public void addRootCategory(List<Datum> list){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        for (int i = 0; i< list.size(); i++){

            Datum val = list.get(i);

            cv.put(Constants.DATABASE.ROOT_ID, val.getId());
            cv.put(Constants.DATABASE.ROOT_PARENT_ID, val.getParentId());
            cv.put(Constants.DATABASE.ROOT_TITLE, val.getTitle());

            try{
                Log.d(LOg_TAG, "Values got  "+ val.getTitle());


                db.insert(Constants.DATABASE.ROOT_CATEGORY_TABLE_NAME, null, cv);

            }catch (Exception e){

                Log.d(LOg_TAG, e+" log");

            }finally {
                //db.close();
            }

        }
        db.close();

    }
    public void addChildCategory(Child val){
        Log.d(LOg_TAG, "Values got  "+ val.getTitle());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constants.DATABASE.CHILD_ID, val.getId());
        cv.put(Constants.DATABASE.CHILD_PARENT_ID, val.getParentId());
        cv.put(Constants.DATABASE.CHILD_TITLE, val.getTitle());

        try{

            db.insert(Constants.DATABASE.CHILD_CATEGORY_TABLE_NAME, null, cv);

        }catch (Exception e){

            Log.d(LOg_TAG, e+" log");

        }finally {
            db.close();
        }
    }

    public void addSubChildCategory(SubChild val) {
        Log.d(LOg_TAG, "Values got  "+ val.getTitle());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constants.DATABASE.SUB_CHILD_ID, val.getId());
        cv.put(Constants.DATABASE.SUB_CHILD_PARENT_ID, val.getParentId());
        cv.put(Constants.DATABASE.SUB_CHILD_TITLE, val.getTitle());

        try{

            db.insert(Constants.DATABASE.SUB_CHILD_CATEGORY_TABLE_NAME, null, cv);

        }catch (Exception e){

            Log.d(LOg_TAG, e+" log");

        }finally {
            db.close();
        }

    }

    public void addDocsCaterories(DocsCategories val) {
        Log.d(LOg_TAG, "Values got  "+ val.getTitle());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constants.DATABASE.DOCS_CATEGORY_ID, val.getId());
        cv.put(Constants.DATABASE.DOCS_CATEGORY_PARENT_ID, val.getParentId());
        cv.put(Constants.DATABASE.DOCS_CATEGORY_TITLE, val.getTitle());

        try{

            db.insert(Constants.DATABASE.DOCS_CATERIES_TABLE_NAME, null, cv);

        }catch (Exception e){

            Log.d(LOg_TAG, e+" log");

        }finally {
            db.close();
        }

    }

    public void addDocsItems(DocsItems val) {
        Log.d(LOg_TAG, "Values got  "+ val.getTitle());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constants.DATABASE.DOCS_ITEM_ID, val.getId());
        cv.put(Constants.DATABASE.DOCS_ITEM_PARENT_ID, val.getSelectionId());
        cv.put(Constants.DATABASE.DOCS_ITEM_TITLE, val.getTitle());
        cv.put(Constants.DATABASE.DOCS_ITEM_FILE, val.getFile());

        try{

            db.insert(Constants.DATABASE.DOCS_ITEMS_TABLE_NAME, null, cv);

        }catch (Exception e){

            Log.d(LOg_TAG, e+" log");

        }finally {
            db.close();
        }


    }

    public void addItems(List<Item> list) {
//
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
       for (int i = 0; i< list.size(); i++){

           Item val = list.get(i);



           cv.put(Constants.DATABASE.ITEM_ID, val.getId());
           cv.put(Constants.DATABASE.ITEM_PARENT_ID, val.getParentId());
           cv.put(Constants.DATABASE.ITEM_TITLE, val.getTitle());
           cv.put(Constants.DATABASE.ITEM_PHARMACOLOGY, val.getPharmacology());
           cv.put(Constants.DATABASE.ITEM_TESTIMONY, val.getTestimony());
           cv.put(Constants.DATABASE.ITEM_DOSAGE, val.getDosage());
           cv.put(Constants.DATABASE.ITEM_CONTRAINDICATIONS, val.getContraindications());
           cv.put(Constants.DATABASE.ITEM_SIDE_EFFECT, val.getSideEffect());
           cv.put(Constants.DATABASE.ITEM_COUNTERPARTS, val.getCounterparts());
           cv.put(Constants.DATABASE.ITEM_MKB10, val.getMkb10());
           cv.put(Constants.DATABASE.ITEM_ADDITIONAL, val.getAdditional());

           try{
               Log.d(LOg_TAG, "Values got  "+ val.getTitle());

               db.insert(Constants.DATABASE.ITEM_TABLE_NAME, null, cv);

           }catch (Exception e){

               Log.d(LOg_TAG, e+" log");

           }finally {
               //db.close();
           }


       }

       db.close();

    }

    public void addItemToFavorite(Item val){
        Log.d(LOg_TAG, "Values got  "+ val.getTitle());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constants.DATABASE.FAVORITE_ITEM_ID, val.getId());
        cv.put(Constants.DATABASE.FAVORITE_TITLE, val.getTitle());
        cv.put(Constants.DATABASE.FAVORITE_TYPE, Constants.REFERENCE.DRUGS);

        try{

            db.insert(Constants.DATABASE.FAVORITE_TABLE_NAME, null, cv);

        }catch (Exception e){

            Log.d(LOg_TAG, e+" log");

        }finally {
            db.close();
        }
    }


    public List<Datum> getRootCategoryList(){
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_CATEGORIES_QUERY + 0, null);

        List<Datum> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Datum datum = new Datum();
                datum.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_TITLE)));
                datum.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_ID)));
                datum.setParentId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_PARENT_ID)));
                list.add(datum);
            }while (cursor.moveToNext());

        }
        return list;
    }
    public void clearTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(Constants.DATABASE.CLEAR_TABLE_QUERY + tableName);

    }

    public List<Child> getChildsFromCategory(int parenId) {
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_CATEGORIES_QUERY + parenId, null);
        List<Child> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Child child = new Child();
                child.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_TITLE)));
                child.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_ID)));
                child.setParentId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_PARENT_ID)));
                list.add(child);
            }while (cursor.moveToNext());

        }
        return list;
    }

    public List<SubChild> getSubChildsFromCategory(int parentId) {

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_SUB_CHILD_CATEGORY_QUERY + parentId, null);
        List<SubChild> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                SubChild child = new SubChild();
                child.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_TITLE)));
                child.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_ID)));
                child.setParentId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ROOT_PARENT_ID)));
                list.add(child);
            }while (cursor.moveToNext());

        }
        return list;
    }

    public List<Item> getItemsList(int parentId){
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_ITEM_QUERY + Constants.DATABASE.ITEM_PARENT_ID
                + " LIKE '%,"+ parentId+",%' or " + Constants.DATABASE.ITEM_PARENT_ID
                + " LIKE '%," + parentId + "%' or " + Constants.DATABASE.ITEM_PARENT_ID
                + " LIKE '%" + parentId + ",%' or " + Constants.DATABASE.ITEM_PARENT_ID
                + " = "  + parentId + " ORDER BY " + Constants.DATABASE.ITEM_TITLE
                , null);
        List<Item> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Item item = new Item();

                item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_TITLE)));
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_ID)));
                item.setParentId(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_PARENT_ID)));
                item.setPharmacology(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_PHARMACOLOGY)));
                item.setTestimony(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_TESTIMONY)));
                item.setContraindications(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_CONTRAINDICATIONS)));
                item.setDosage(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_DOSAGE)));
                item.setSideEffect(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_SIDE_EFFECT)));
                item.setCounterparts(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_COUNTERPARTS)));
                item.setMkb10(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_MKB10)));
                item.setAdditional(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_ADDITIONAL)));





                list.add(item);
            }while (cursor.moveToNext());

        }
        return list;

    }

    public Item getItemObj(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_ITEM_QUERY + Constants.DATABASE.ITEM_ID +" = " + id, null);
        Item item = new Item();

        if(cursor.moveToFirst()){
            do{


                item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_TITLE)));
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_ID)));
                item.setParentId(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_PARENT_ID)));
                item.setPharmacology(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_PHARMACOLOGY)));
                item.setTestimony(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_TESTIMONY)));
                item.setContraindications(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_CONTRAINDICATIONS)));
                item.setDosage(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_DOSAGE)));
                item.setSideEffect(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_SIDE_EFFECT)));
                item.setCounterparts(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_COUNTERPARTS)));
                item.setMkb10(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_MKB10)));
                item.setAdditional(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_ADDITIONAL)));
            }while (cursor.moveToNext());

        }

        return item;

    }

    public List<DocsCategories> getDocsCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_DOCS_CATEGORIES_QUERY, null);
        List<DocsCategories> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                DocsCategories item = new DocsCategories();
                item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_TITLE)));
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_ID)));
                item.setParentId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_PARENT_ID)));
                list.add(item);
            }while (cursor.moveToNext());

        }
        return list;
    }

    public List<DocsCategories> getDocsCategoriesForMain() {
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_DOCS_CATEGORIES_QUERY + " WHERE " +
                Constants.DATABASE.DOCS_CATEGORY_PARENT_ID + " = 0", null);
        List<DocsCategories> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                DocsCategories item = new DocsCategories();
                item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_TITLE)));
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_ID)));
                item.setParentId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_PARENT_ID)));
                list.add(item);
            }while (cursor.moveToNext());

        }
        return list;
    }

    public List<DocsItems> getDocsItems(int parentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_DOCS_ITEMS_QUERY + parentId, null);
        List<DocsItems> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                DocsItems item = new DocsItems();
                item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_ITEM_TITLE)));
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_ITEM_ID)));
                item.setFile(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_ITEM_FILE)));

                list.add(item);
            }while (cursor.moveToNext());

        }

        return list;


    }

    public List<SearchObj> getItemsBySearch(String newText) {

        if(newText.length()==0){
            List<SearchObj> list = new ArrayList<>();

            return list;

        }else{
            SQLiteDatabase db = this.getReadableDatabase();

            List<SearchObj> list = new ArrayList<>();

            cursor = db.rawQuery(Constants.DATABASE.GET_ITEM_QUERY  +
                    "LOWER ("+ Constants.DATABASE.ITEM_TITLE+")  LIKE LOWER('%" + newText + "%') OR "
                    + Constants.DATABASE.ITEM_COUNTERPARTS + " LIKE LOWER('%"+ newText + "%')" , null);


            if(cursor.moveToFirst()){
                do{
                    SearchObj item = new SearchObj();
                    item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_TITLE)));
                    item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_ID)));
                    item.setType("DRUGS");
                    if(!item.getTitle().contains(newText)&&newText.length()>0){
                        Log.d(LOg_TAG, !item.getTitle().contains(newText) + " Analog Text in query");
                        item.setAnalog(true);
                        item.setAnalogTxt(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_COUNTERPARTS)));

                        Log.d(LOg_TAG, cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_COUNTERPARTS)) + "anlogs");
                    }

                    list.add(item);


                }while (cursor.moveToNext());

            }

            cursor = db.rawQuery(Constants.DATABASE.GET_DOCS_SEARC_QUERY  +
                    "LOWER ("+ Constants.DATABASE.DOCS_ITEM_TITLE+")  LIKE LOWER('" + newText + "%')", null);


            if(cursor.moveToFirst()){
                do{
                    SearchObj item = new SearchObj();
                    item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_TITLE)));
                    item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.ITEM_ID)));
                    item.setType("DOCS");
                    list.add(item);
                }while (cursor.moveToNext());

            }

            return list;

        }

        }

    public void removeFromFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL(Constants.DATABASE.DELETE_FROM_FAVORITE_QUERY + id);

        }catch (Exception e){

        }
        db.close();

    }

    public boolean isInFavorite(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_FAVORITE_QUERY +
                Constants.DATABASE.FAVORITE_ITEM_ID + " = " + id, null);
        Log.d(LOg_TAG, cursor.getCount() + " In cursor favorite");

        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }

    }

    public List<FavoriteObj> getFavoriteList() {
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_FAVORITES_QUERY, null);
        List<FavoriteObj> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                FavoriteObj item = new FavoriteObj();
                item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.FAVORITE_TITLE)));
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.FAVORITE_ITEM_ID)));
                item.setType(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.FAVORITE_TYPE)));

                list.add(item);
            }while (cursor.moveToNext());

        }

        return list;
    }


    public void addDocsCaterories(ua.benya.medicaldirectory.data.pojo.documents.categories.Child val) {
        Log.d(LOg_TAG, "Values got  "+ val.getTitle());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Constants.DATABASE.DOCS_CATEGORY_ID, val.getId());
        cv.put(Constants.DATABASE.DOCS_CATEGORY_PARENT_ID, val.getParentId());
        cv.put(Constants.DATABASE.DOCS_CATEGORY_TITLE, val.getTitle());

        try{

            db.insert(Constants.DATABASE.DOCS_CATERIES_TABLE_NAME, null, cv);

        }catch (Exception e){

            Log.d(LOg_TAG, e+" log");

        }finally {
            db.close();
        }
    }

    public List<ua.benya.medicaldirectory.data.pojo.documents.categories.Child> getDocsChildCategories(int parentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(Constants.DATABASE.GET_DOCS_SUB_CATEGORIES_QUERY + parentId, null);
        List<ua.benya.medicaldirectory.data.pojo.documents.categories.Child> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                ua.benya.medicaldirectory.data.pojo.documents.categories.Child item =
                        new ua.benya.medicaldirectory.data.pojo.documents.categories.Child();
                item.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_TITLE)));
                item.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_ID)));
                item.setParentId(cursor.getInt(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_CATEGORY_PARENT_ID)));
                //item.setFile(cursor.getString(cursor.getColumnIndexOrThrow(Constants.DATABASE.DOCS_ITEM_FILE)));

                list.add(item);

            }while (cursor.moveToNext());

        }

        return list;

    }
}
