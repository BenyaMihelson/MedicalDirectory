package ua.benya.medicaldirectory;

import android.app.Application;
import android.support.annotation.NonNull;

import ua.benya.medicaldirectory.data.database.ApplicationDataBase;

/**
 * Created by Shipohvost on 10.11.2016.
 */

public class MyApp extends Application {
    private static MyApp sInstance;
    private static ApplicationDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        dataBase = new ApplicationDataBase(sInstance);



    }
    @NonNull
    public static MyApp getContext(){
        return sInstance;
    }

    private boolean isAlcjDay(String day){
        if(day.equals("Среда") || day.equals("Четверг")){
            return true;
        }
        return false;
    }


}
