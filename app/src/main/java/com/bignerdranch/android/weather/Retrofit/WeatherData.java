package com.bignerdranch.android.weather.Retrofit;


public class WeatherData {

    String temperature;
    int icon;
    String cityName;
    String summary;
    String apparentTemperature;
    String time;

    public WeatherData(String temperature, int icon, String cityName, String summary, String apparentTemperature, String time) {
        this.temperature = temperature;
        this.icon = icon;
        this.cityName = cityName;
        this.summary = summary;
        this.apparentTemperature = apparentTemperature;
        this.time = time;
    }
}
