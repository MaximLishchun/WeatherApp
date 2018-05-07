package com.bignerdranch.android.weather.Room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DBTemperatureObject.class}, version = 1)
public abstract class  DataBase extends RoomDatabase {

    public abstract TemperatureDao getTemperatureDao();

}

