package com.example.artem.wheatherapp.adapter;

/**
 * Created by Artem on 30.01.2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.artem.wheatherapp.R;
import com.example.artem.wheatherapp.model.listweather.ListWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<ArrayList<ListWeather>> weatherList;
    private WeatherClickListener mListener;

    public RecyclerAdapter(WeatherClickListener listener) {
        weatherList = new ArrayList<>();
        mListener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        ArrayList<ListWeather> weather = weatherList.get(position);
        holder.tempText.setText(FormatTemp(weather.get(position).getMain().getTemp()));
        holder.descriptionText.setText(weather.get(position).getWeather().get(0).getDescription());
        holder.dateText.setText(weather.get(position).getDt_txt());
        Picasso.with(holder.itemView.getContext())
                .load("http://openweathermap.org/img/w/" + weather.get(position).getWeather().get(0).getIcon() + ".png")
                .into(holder.weatherImage);
        getBackground(holder, weather.get(position).getDt_txt());
    }

    private void getBackground(ViewHolder holder, String sDate) {
        String date = sDate.substring(11);
        switch (date){
            case "12:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.daymini);
                break;
            case "15:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.daymini);
                break;
            case "18:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.sunsetmini);
                break;
            case "21:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.sunsetmini);
                break;
            case "00:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.nightmini);
                break;
            case "03:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.nightmini);
                break;
            case "06:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.sunrisemini);
                break;
            case "09:00:00":
                holder.relativeLayout.setBackgroundResource(R.drawable.sunrisemini);
                break;
        }

    }

    public int getItemCount() {
        return weatherList.size();
    }

    public ArrayList<ListWeather> getSelectedWeather(int position) {
        return weatherList.get(position);
    }

    public static String FormatTemp(String temp) {
        float i = Float.parseFloat(temp) - 273;
        return String.format("%.2f",i) + "\u00B0";

    }

    public void addData(ArrayList<ListWeather> weathers) {
        weatherList.add(weathers);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.relativeLayout)
        public RelativeLayout relativeLayout;
        @BindView(R.id.tempText)
        public TextView tempText;
        @BindView(R.id.descriptionText)
        public TextView descriptionText;
        @BindView(R.id.weatherImage)
        public ImageView weatherImage;
        @BindView(R.id.dateText)
        public TextView dateText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition());
        }
    }

}
