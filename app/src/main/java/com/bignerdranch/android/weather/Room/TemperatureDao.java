package com.bignerdranch.android.weather.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TemperatureDao {

    @Insert
    void saveTemperature(DBTemperatureObject temperature);

    @Delete
    void deleteTemperature(DBTemperatureObject temperature);

    @Query("SELECT * FROM temperature")
    List<DBTemperatureObject> getAllTemperature();
}
