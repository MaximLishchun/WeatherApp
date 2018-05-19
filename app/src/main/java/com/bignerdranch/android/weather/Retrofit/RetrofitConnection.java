package com.bignerdranch.android.weather.Retrofit;


import com.bignerdranch.android.weather.ConnectServer.ApiService;
import com.bignerdranch.android.weather.ConnectServer.GetPostTemperature;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitConnection {

    private final String lng = "28.48";
    private final String lat = "49.23";
    private final String apiId = GetPostTemperature.API;

    private ApiService apiService;
    private Call<GetPostTemperature> call;
    private OnDoResponse onDoResponse;

    public RetrofitConnection() {
        apiService = RetrofitBuilder.getRetrofit().create(ApiService.class);
        call = apiService.getMyJSON(apiId, lat, lng);
        call.enqueue(new Callback<GetPostTemperature>() {
            @Override
            public void onResponse(Call<GetPostTemperature> call, Response<GetPostTemperature> response) {
                if(onDoResponse != null)
                    onDoResponse.doResponse(response);
            }

            @Override
            public void onFailure(Call<GetPostTemperature> call, Throwable t) {

            }
        });
    }

    public void setOnDoResponse (OnDoResponse onDoResponse){
        this.onDoResponse = onDoResponse;
    }
}
