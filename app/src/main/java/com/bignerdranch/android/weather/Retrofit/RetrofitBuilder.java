package com.bignerdranch.android.weather.Retrofit;

import com.bignerdranch.android.weather.ConnectServer.GetPostTemperature;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder{

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logger);
        if (retrofit == null){
            return new Retrofit.Builder().baseUrl(GetPostTemperature.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        } else return retrofit;
    }
}
