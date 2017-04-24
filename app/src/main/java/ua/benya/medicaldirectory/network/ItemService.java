package ua.benya.medicaldirectory.network;

import retrofit2.Call;
import retrofit2.http.GET;
import ua.benya.medicaldirectory.data.pojo.UpdateTimeObj;
import ua.benya.medicaldirectory.data.pojo.documents.categories.DocsCategoriesDatum;
import ua.benya.medicaldirectory.data.pojo.documents.items.DocsItemsDatum;
import ua.benya.medicaldirectory.data.pojo.drugs.categories.RootCategory;
import ua.benya.medicaldirectory.data.pojo.drugs.items.ItemObj;

/**
 * Created by Shipohvost on 11.11.2016.
 */

public interface ItemService {
    /*@GET("api.php?method=getSelections&password=LW2Q5mRnmb")
    Call<RootCategory> getCategoryObj();
*/
    @GET("api.php?method=getCategories&password=LW2Q5mRnmb")
    Call<RootCategory> getCategoryObj();

    @GET("api.php?method=getDrugs&password=LW2Q5mRnmb")
    Call<ItemObj> getItemObg();



    @GET("api.php?method=getSelectionsDocuments&password=LW2Q5mRnmb")
    Call<DocsCategoriesDatum> getDocs();

    @GET("api.php?method=getDocuments&password=LW2Q5mRnmb")
    Call<DocsItemsDatum> getDocsItem();

    @GET("api.php?method=getLastUpdate&password=LW2Q5mRnmb")
    Call<UpdateTimeObj> getTime();








}
