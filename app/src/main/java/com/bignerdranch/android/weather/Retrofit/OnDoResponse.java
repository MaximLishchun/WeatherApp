package com.bignerdranch.android.weather.Retrofit;


import com.bignerdranch.android.weather.ConnectServer.GetPostTemperature;

public interface OnDoResponse {
    void doResponse(retrofit2.Response<GetPostTemperature> response);
}
