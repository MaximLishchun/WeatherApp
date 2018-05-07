package com.bignerdranch.android.weather.ConnectServer;


import com.bignerdranch.android.weather.ConnectServer.ObjectJSON.Currently;
import com.bignerdranch.android.weather.ConnectServer.ObjectJSON.Daily;
import com.bignerdranch.android.weather.ConnectServer.ObjectJSON.Datum;
import com.bignerdranch.android.weather.ConnectServer.ObjectJSON.Datum_;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetPostTemperature {

    public static final String API = "7581df654b252869f646133808a27027";
    public static final String BASE_URL = "https://api.darksky.net/";

    @SerializedName("currently")
    @Expose
    private Currently currently;

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }


    @SerializedName("daily")
    @Expose
    private Daily daily;

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    @SerializedName("timezone")
    @Expose
    private String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
}
