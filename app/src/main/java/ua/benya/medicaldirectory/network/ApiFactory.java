package ua.benya.medicaldirectory.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.benya.medicaldirectory.helper.Constants;

/**
 * Created by Shipohvost on 11.11.2016.
 */

public class ApiFactory {
    private static ItemService itemService;

    public ItemService getItemService(){
        if (itemService == null){
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();



            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            itemService = retrofit.create(ItemService.class);
        }
        return itemService;
    }



}
