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
import com.example.artem.wheatherapp.model.ListWeather;
import com.example.artem.wheatherapp.model.ModelWeather;

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
//        Log.d("Log", "onResponse: \n" + "Temp: " + weather);
        holder.tempText.setText(weather.get(position).getMain().getTemp().toString());
        holder.descriptionText.setText(weather.get(position).getWeather().get(0).getDescription());
//        Picasso.with(holder.itemView.getContext()).load(weather.getListWeather().get(position).getWeather().get(0).getIcon()).into(holder.weatherImage);

    }

//    public void addData(ModelWeather listWeather) {
//        weatherList.add(listWeather);
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
