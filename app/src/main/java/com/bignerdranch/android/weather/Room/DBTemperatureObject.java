package com.bignerdranch.android.weather.Room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "temperature")
public class DBTemperatureObject {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "temp")
    public String temp;

    @ColumnInfo(name = "summary")
    public String summary;

    @ColumnInfo(name = "timezone")
    public String timezone;

    @ColumnInfo(name = "icon")
    public int icon;

    @ColumnInfo(name = "apparentTemperature")
    public String apparentTemperature;

    @ColumnInfo(name = "date")
    public String date;

}
