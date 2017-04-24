package ua.benya.medicaldirectory.helper;

/**
 * Created by Shipohvost on 14.11.2016.
 */

public class Constants {
    public static final class HTTP{
        public static final String FILES_DOWLOADED_URL = "http://tekhnicheskiy.ru.swtest.ru/content/";

    }
    public static final class API{
        public static final String BASE_URL = "http://tekhnicheskiy.ru.swtest.ru/";
    }

    public static final class REFERENCE{

        public static final String DOCS = "docs";
        public static final String DRUGS = "drugs";

            }

    public static final class DATABASE {

        public static final String DB_NAME = "appDB";
        public static final String ROOT_CATEGORY_TABLE_NAME = "root_category";
        public static final String CHILD_CATEGORY_TABLE_NAME = "child_category";
        public static final String SUB_CHILD_CATEGORY_TABLE_NAME = "sub_child_category";
        public static final String ITEM_TABLE_NAME = "item_table";
        public static final String DOCS_CATERIES_TABLE_NAME = "docs";
        public static final String DOCS_ITEMS_TABLE_NAME = "docs_items";
        public static final String FAVORITE_TABLE_NAME = "favorite";

        public static final int DB_VERSION =10;



       /* * fields of CHILD_CATEGORY_TABLE
        */
        public static final String FAVORITE_AUTO_ID = "_id";
        public static final String FAVORITE_ITEM_ID = "id";
        public static final String FAVORITE_TYPE = "type";
        public static final String FAVORITE_TITLE = "title";
        /*

           /*
      * fields of ROOT_CATEGORY_TABLE
      *
      * */
        public static final String ROOT_AUTO_ID = "_id";
        public static final String ROOT_ID = "id";
        public static final String ROOT_PARENT_ID = "parent_id";
        public static final String ROOT_TITLE = "title";
        public static final String ROOT_TIME = "time";

        /*
        *  end of fields ROOT_CATEGORY_TABLE
        *
        * */
        /*
   * fields of CHILD_CATEGORY_TABLE
   *
   * */
        public static final String CHILD_AUTO_ID = "_id";
        public static final String CHILD_ID = "id";
        public static final String CHILD_PARENT_ID = "parent_id";
        public static final String CHILD_TITLE = "title";
     /*
   * fields of CHILD_CATEGORY_TABLE
   *
   * */
        public static final String SUB_CHILD_AUTO_ID = "_id";
        public static final String SUB_CHILD_ID = "id";
        public static final String SUB_CHILD_PARENT_ID = "parent_id";
        public static final String SUB_CHILD_TITLE = "title";

     /*
   * fields of CHILD_CATEGORY_TABLE
   *
   * */
        public static final String ITEM_AUTO_ID = "_id";
        public static final String ITEM_ID = "id";
        public static final String ITEM_PARENT_ID = "parent_id";
        public static final String ITEM_TITLE = "title";
        public static final String ITEM_PHARMACOLOGY = "pharmacology";
        public static final String ITEM_TESTIMONY = "testimony";
        public static final String ITEM_CONTRAINDICATIONS = "contraindications";
        public static final String ITEM_DOSAGE = "dosage";
        public static final String ITEM_SIDE_EFFECT = "side_effect";
        public static final String ITEM_COUNTERPARTS = "counterparts";
        public static final String ITEM_MKB10 = "mkb10";
        public static final String ITEM_ADDITIONAL = "additional";
        public static final String ITEM_ISFAVORITE = "favorite";

        /*
      * fields of DOCS_CATEGORY
      *
      * */
        public static final String DOCS_CATEGORY_AUTO_ID = "_id";
        public static final String DOCS_CATEGORY_ID = "id";
        public static final String DOCS_CATEGORY_PARENT_ID = "parent_id";
        public static final String DOCS_CATEGORY_TITLE = "title";

        /*
      * fields of DOCS_ITEMS
      *
      * */
        public static final String DOCS_ITEM_AUTO_ID = "_id";
        public static final String DOCS_ITEM_ID = "id";
        public static final String DOCS_ITEM_PARENT_ID = "parent_id";
        public static final String DOCS_ITEM_FILE = "file";
        public static final String DOCS_ITEM_TITLE = "title";

        public static final String ROOT_CATEGORY_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS " + ROOT_CATEGORY_TABLE_NAME;
        public static final String CHILD_CATEGORY_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS " + CHILD_CATEGORY_TABLE_NAME;
        public static final String SUB_CHILD_CATEGORY_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS " + SUB_CHILD_CATEGORY_TABLE_NAME;
        public static final String ITEM_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS " + ITEM_TABLE_NAME;
        public static final String DOCS_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS " + DOCS_CATERIES_TABLE_NAME;
        public static final String DOCS_ITEM_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS " + DOCS_ITEMS_TABLE_NAME;
        public static final String FAVORITE_TABLE_DROP_QUERY = "DROP TABLE IF EXISTS " + FAVORITE_TABLE_NAME;


        public static final String GET_CATEGORIES_QUERY = "SELECT * FROM " + ROOT_CATEGORY_TABLE_NAME + " WHERE " + CHILD_PARENT_ID + " = ";
        public static final String GET_CHILD_CATEGORY_QUERY = "SELECT * FROM "
                + CHILD_CATEGORY_TABLE_NAME + " WHERE " + CHILD_PARENT_ID + " = ";

        public static final String GET_SUB_CHILD_CATEGORY_QUERY = "SELECT * FROM "
                + SUB_CHILD_CATEGORY_TABLE_NAME + " WHERE " + SUB_CHILD_PARENT_ID + " = ";

        public static final String GET_ITEM_QUERY = "SELECT * FROM " + ITEM_TABLE_NAME + " WHERE ";

        public static final String GET_DOCS_CATEGORIES_QUERY = "SELECT * FROM " + DOCS_CATERIES_TABLE_NAME;

        public static final String GET_DOCS_SUB_CATEGORIES_QUERY = "SELECT * FROM " + DOCS_CATERIES_TABLE_NAME + " WHERE " + DOCS_CATEGORY_PARENT_ID + " = ";


        public static final String GET_DOCS_ITEMS_QUERY = "SELECT * FROM  " + DOCS_ITEMS_TABLE_NAME + " WHERE " + DOCS_ITEM_PARENT_ID + " = ";

        public static final String GET_DOCS_SEARC_QUERY = "SELECT * FROM " + DOCS_ITEMS_TABLE_NAME + " WHERE ";

        public static final String GET_FAVORITE_QUERY = "SELECT * FROM " + FAVORITE_TABLE_NAME + " WHERE ";

        public static final String GET_FAVORITES_QUERY = "SELECT * FROM " + FAVORITE_TABLE_NAME;

        public static final String DELETE_FROM_FAVORITE_QUERY = "DELETE FROM " + FAVORITE_TABLE_NAME + " WHERE "
                + FAVORITE_ITEM_ID + " = ";





        public static final String CREATE_ROOT_CATEGORY_TABLE_QUERY = "create table " + ROOT_CATEGORY_TABLE_NAME
                + " (" + ROOT_AUTO_ID +
                " integer primary key autoincrement, "  + ROOT_TITLE +
                " text, " + ROOT_ID + " integer, " + ROOT_PARENT_ID + " integer, "  + ROOT_TIME + " integer);";

        public static final String CREATE_CHILD_CATEGORY_TABLE_QUERY = "create table " + CHILD_CATEGORY_TABLE_NAME
                + " (" + CHILD_AUTO_ID +
                " integer primary key autoincrement, "  + CHILD_TITLE +
                " text, " + CHILD_ID + " integer, " + CHILD_PARENT_ID + " integer);";

        public static final String CREATE_SUB_CHILD_CATEGORY_TABLE_QUERY = "create table " + SUB_CHILD_CATEGORY_TABLE_NAME
                + " (" + SUB_CHILD_AUTO_ID +
                " integer primary key autoincrement, "  + SUB_CHILD_TITLE +
                " text, " + SUB_CHILD_ID + " integer, " + SUB_CHILD_PARENT_ID + " integer);";

        public static final String CREATE_ITEM_TABLE_QUERY = "create table " + ITEM_TABLE_NAME
                + " (" + ITEM_AUTO_ID +
                " integer primary key autoincrement, "  + ITEM_TITLE +
                " text, " + ITEM_ID + " integer, " + ITEM_PHARMACOLOGY + " text, " + ITEM_TESTIMONY
                + " text, " + ITEM_CONTRAINDICATIONS + " text, " + ITEM_DOSAGE + " text, "
                + ITEM_SIDE_EFFECT + " text, " + ITEM_COUNTERPARTS + " text, " + ITEM_MKB10 +
                " text, " + ITEM_ADDITIONAL + " text, " + ITEM_ISFAVORITE + " integer, " + ITEM_PARENT_ID + " string);";

        public static final String CREATE_DOCUMENTS_TABLE_QUERY = "create table " + DOCS_CATERIES_TABLE_NAME
                + " (" + DOCS_CATEGORY_AUTO_ID +
                " integer primary key autoincrement, "  + DOCS_CATEGORY_TITLE +
                " text, " + DOCS_CATEGORY_ID + " integer, " + DOCS_CATEGORY_PARENT_ID + " integer);";

        public static final String CREATE_DOCUMENTS_ITEMS_TABLE_QUERY = "create table " + DOCS_ITEMS_TABLE_NAME
                + " (" + DOCS_ITEM_AUTO_ID +
                " integer primary key autoincrement, "  + DOCS_ITEM_TITLE +
                " text, "  + DOCS_ITEM_FILE +
                " text, " + DOCS_ITEM_ID + " integer, " + DOCS_ITEM_PARENT_ID + " integer);";

        public static final String CREATE_FAVORITE_TABLE_QUERY = "create table " + FAVORITE_TABLE_NAME
                + " (" + FAVORITE_AUTO_ID +
                " integer primary key autoincrement, "  + FAVORITE_TITLE +
                " text, " + FAVORITE_ITEM_ID + " integer, " + FAVORITE_TYPE + " text);";






        public static final String CLEAR_TABLE_QUERY = "DELETE  FROM ";

    }


}
