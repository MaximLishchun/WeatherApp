package com.bignerdranch.android.weather.ConnectServer;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("forecast/{key}/{latitude},{longitude}")
    Call<GetPostTemperature> getMyJSON(
            @Path("key") String appid,
            @Path("latitude") String lat,
            @Path("longitude") String lon);
}
