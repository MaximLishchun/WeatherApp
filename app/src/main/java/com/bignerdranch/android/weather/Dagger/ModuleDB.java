package com.bignerdranch.android.weather.Dagger;


import android.support.annotation.NonNull;

import com.bignerdranch.android.weather.Room.DBTemperatureObject;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuleDB {

    @Provides
    @NonNull
    @Singleton
    public DBTemperatureObject createDbObject(){
        return new DBTemperatureObject();
    }
}
