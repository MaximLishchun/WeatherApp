package com.bignerdranch.android.weather.Room;


import android.app.Application;
import android.arch.persistence.room.Room;


public class AppRoom extends Application{

    public static AppRoom instance;

    private DataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataBase = Room.databaseBuilder(this, DataBase.class, "temperature").build();
    }

    public  static AppRoom getInstance(){
        return instance;
    }

    public DataBase getDataBase(){
        return dataBase;
    }
}
