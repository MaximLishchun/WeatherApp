package com.bignerdranch.android.weather.Retrofit;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.weather.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.WeatherDataViewHolder>{

    private List<WeatherData> weatherData;

    public RecyclerAdapter (List<WeatherData> weatherData){
        this.weatherData = weatherData;
    }

    @Override
    public WeatherDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_layout, parent, false);
        WeatherDataViewHolder weatherDataViewHolder = new WeatherDataViewHolder(view);
        return weatherDataViewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherDataViewHolder holder, int position) {
        holder.temperature.setText(weatherData.get(position).temperature);
        holder.icon.setImageResource(weatherData.get(position).icon);
        holder.cityName.setText(weatherData.get(position).cityName);
        holder.summary.setText(weatherData.get(position).summary);
        holder.apparentTemperature.setText(weatherData.get(position).apparentTemperature);
        holder.time.setText(weatherData.get(position).time);
    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class WeatherDataViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView temperature;
        ImageView icon;
        TextView cityName;
        TextView summary;
        TextView apparentTemperature;
        TextView time;

        public WeatherDataViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cv);
            temperature = (TextView) itemView.findViewById(R.id.temperature);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            cityName = (TextView) itemView.findViewById(R.id.cityName);
            summary = (TextView) itemView.findViewById(R.id.summary);
            apparentTemperature = (TextView) itemView.findViewById(R.id.apparentTemperature);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
