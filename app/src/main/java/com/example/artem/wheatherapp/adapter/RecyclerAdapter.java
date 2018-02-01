package com.example.artem.wheatherapp.adapter;

/**
 * Created by Artem on 30.01.2018.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.artem.wheatherapp.R;
import com.example.artem.wheatherapp.api.RestManager;
import com.example.artem.wheatherapp.model.listweather.ListWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<ArrayList<ListWeather>> weatherList;

    public RecyclerAdapter(List<ArrayList<ListWeather>> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ArrayList<ListWeather> weather = weatherList.get(position);
        Log.d("Log", "onResponse: \n" + "Temp: " + RestManager.BASE_URL + weather.get(position).getWeather().get(0).getIcon());
        holder.tempText.setText(FormatTemp(weather.get(position).getMain().getTemp()));
        holder.descriptionText.setText(weather.get(position).getWeather().get(0).getDescription());
        holder.dateText.setText(weather.get(position).getDt_txt());
        Picasso.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + weather.get(position).getWeather().get(0).getIcon() + ".png")
                .into(holder.weatherImage);


    }

    public String FormatTemp(String temp) {
        float i = Float.parseFloat(temp) - 273;
        return String.format("%.2f",i);

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
