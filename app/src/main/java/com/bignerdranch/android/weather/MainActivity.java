package com.bignerdranch.android.weather;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.weather.ConnectServer.ApiService;
import com.bignerdranch.android.weather.ConnectServer.GetPostTemperature;
import com.bignerdranch.android.weather.ConnectServer.ObjectJSON.Currently;
import com.bignerdranch.android.weather.ConnectServer.ObjectJSON.Datum_;
import com.bignerdranch.android.weather.Retrofit.RecyclerAdapter;
import com.bignerdranch.android.weather.Retrofit.RetrofitBuilder;
import com.bignerdranch.android.weather.Retrofit.WeatherData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private List<WeatherData> weatherData;

    private String summary;
    private String timezone;
    private int icon;
    private String temperature;
    private String apparentTemperature;
    private String date;

    private String summaryNext;
    private int iconNext;
    private String temperatureNextMax;
    private String apparentTemperatureNextMax;
    private String temperatureNextMin;
    private String apparentTemperatureNextMin;
    private String dateNext;

    private RecyclerView rv;
    private LinearLayoutManager llm;
    private RecyclerAdapter adapter;

    private ProgressBar progressBar;
    private TextView progressText;
    private final long timeInMills = 3000;

    private Boolean exit = false;

    private CheckNetworkConnection networkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherData = new ArrayList<>();

        rv = (RecyclerView) findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressText = (TextView) findViewById(R.id.progressText);

        connected();
    }

//    @Override
//    public void onBackPressed() {
//        if (exit) {
//            finish();
//        } else {
//            exit = true;
//            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
//            new Handler().postDelayed(() -> exit = false, 3 * 1000);
//        }
//    }

    private  void connected(){
        if(!networkConnection.isNetworkConnected(this)){
            Intent intent = new Intent(this, ConnectionActivity.class);
            startActivity(intent);
        }else {
            ProgressAsyncTask progressAsyncTask = new ProgressAsyncTask();
            progressAsyncTask.execute();
        }
    }

    private void doRequest() {
        retrofit = RetrofitBuilder.getRetrofit();

        String lng = "28.48";
        String lat = "49.23";
        String apiId = GetPostTemperature.API;

        ApiService apiService = retrofit.create(ApiService.class);

        Call<GetPostTemperature> call = apiService.getMyJSON(apiId, lat, lng);

        call.enqueue(new Callback<GetPostTemperature>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<GetPostTemperature> call, Response<GetPostTemperature> response) {
                if (response.isSuccessful()) {
                    GetPostTemperature data = response.body();
                    Currently currently = data.getCurrently();
                    List<Datum_> datum_list = data.getDaily().getData();
                    timezone = data.getTimezone();

                    String iconWeather;
                    long time;

                    time = currently.getTime();

                    summary = currently.getSummary();
                    iconWeather = currently.getIcon();
                    temperature = String.valueOf("Now: " + Math.round(currently.getTemperature()) + "°C");
                    apparentTemperature = String.valueOf("Feels like: "
                            + Math.round(currently.getApparentTemperature()) + "°C");
                    date = new java.text.SimpleDateFormat("dd.MM.yyyy")
                            .format(new java.util.Date(time * 1000));

                    switch (iconWeather) {
                        case "clear-day":
                            icon = R.drawable.clear_day_icon;
                            break;
                        case "clear-night":
                            icon = R.drawable.clear_night;
                            break;
                        case "partly-cloudy-day":
                            icon = R.drawable.partly_cloudy_day;
                            break;
                        case "partly-cloudy-night":
                            icon = R.drawable.partly_cloudy_night;
                            break;
                        case "rain":
                            icon = R.drawable.cloud_rain;
                            break;
                        case "cloudy":
                            icon = R.drawable.cloudy;
                            break;
                        default:
                            icon = R.drawable.partly_cloudy_day;
                    }
                    weatherData.add(new WeatherData(temperature, icon, timezone, summary, apparentTemperature, date));

                    long timeNext;
                    String iconWeatherNext;
                    for (int i = 0; i < datum_list.size(); i++) {
                        Datum_ datum_ = datum_list.get(i);

                        summaryNext = datum_.getSummary();

                        iconWeatherNext = datum_.getIcon();

                        switch (iconWeatherNext) {
                            case "clear-day":
                                iconNext = R.drawable.clear_day_icon;
                                break;
                            case "clear-night":
                                iconNext = R.drawable.clear_night;
                                break;
                            case "partly-cloudy-day":
                                iconNext = R.drawable.partly_cloudy_day;
                                break;
                            case "partly-cloudy-night":
                                iconNext = R.drawable.partly_cloudy_night;
                                break;
                            case "cloudy":
                                icon = R.drawable.cloudy;
                                break;
                            case "rain":
                                icon = R.drawable.cloud_rain;
                                break;
                            default:
                                icon = R.drawable.partly_cloudy_day;
                        }

                        temperatureNextMax = String.valueOf("Max: " + Math.round(datum_.getTemperatureMax()) + "°C");
                        temperatureNextMin = String.valueOf("Min: " + Math.round(datum_.getTemperatureMin()) + "°C");

                        apparentTemperatureNextMax = String.valueOf("Feels like: "
                                + Math.round(datum_.getApparentTemperatureMax()) + "°C");
                        apparentTemperatureNextMin = String.valueOf("Feels like: "
                                + Math.round(datum_.getApparentTemperatureMin()) + "°C");

                        timeNext = datum_.getTime();
                        dateNext = new java.text.SimpleDateFormat("dd.MM.yyyy")
                                .format(new java.util.Date(timeNext * 1000));

                        weatherData.add(new WeatherData(temperatureNextMax, iconNext, timezone,
                                summaryNext, apparentTemperatureNextMax, dateNext));
                        weatherData.add(new WeatherData(temperatureNextMin, iconNext, timezone,
                                summaryNext, apparentTemperatureNextMin, dateNext));
                    }

                    adapter = new RecyclerAdapter(weatherData);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<GetPostTemperature> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(),
                        throwable.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ProgressAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressText.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
            doRequest();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(timeInMills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressText.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }
}