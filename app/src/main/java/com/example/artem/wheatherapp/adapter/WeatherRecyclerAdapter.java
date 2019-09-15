package com.example.artem.wheatherapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artem.wheatherapp.R;
import com.example.artem.wheatherapp.WeatherDetailActivity;
import com.example.artem.wheatherapp.model.ModelWeather;
import com.squareup.picasso.Picasso;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    private ModelWeather model;

    public WeatherRecyclerAdapter(ModelWeather modelWeather) {
        this.model = modelWeather;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tempText.setText(FormatTemp(model.getListWeather().get(position).getMain().getTemp()));
        holder.descriptionText.setText(model.getListWeather().get(position).getWeather().get(0).getDescription());
        holder.dateText.setText(model.getListWeather().get(position).getDt_txt());
        Picasso.get()
                .load("http://openweathermap.org/img/w/" + model.getListWeather()
                        .get(position).getWeather().get(0).getIcon() + ".png")
                .into(holder.weatherImage);
        getBackground(holder, model.getListWeather().get(position).getDt_txt());

        holder.cardView.setOnClickListener(view ->
                holder.itemView.getContext().startActivity(transition(holder.itemView.getContext(), model, position)));
    }

    private void getBackground(ViewHolder holder, String sDate) {
        String date = sDate.substring(11);
        switch (date) {
            case "12:00:00":
                holder.cardView.setBackgroundResource(R.drawable.daymini);
                break;
            case "15:00:00":
                holder.cardView.setBackgroundResource(R.drawable.daymini);
                break;
            case "18:00:00":
                holder.cardView.setBackgroundResource(R.drawable.sunsetmini);
                break;
            case "21:00:00":
                holder.cardView.setBackgroundResource(R.drawable.sunsetmini);
                break;
            case "00:00:00":
                holder.cardView.setBackgroundResource(R.drawable.nightmini);
                break;
            case "03:00:00":
                holder.cardView.setBackgroundResource(R.drawable.nightmini);
                break;
            case "06:00:00":
                holder.cardView.setBackgroundResource(R.drawable.sunrisemini);
                break;
            case "09:00:00":
                holder.cardView.setBackgroundResource(R.drawable.sunrisemini);
                break;
        }

    }

    public int getItemCount() {
        return 35;
    }

    @SuppressLint("DefaultLocale")
    public static String FormatTemp(String temp) {
        float i = Float.parseFloat(temp) - 273;
        return String.format("%.2f", i);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView tempText;
        private TextView descriptionText;
        private ImageView weatherImage;
        private TextView dateText;

        private ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            tempText = itemView.findViewById(R.id.tempText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            weatherImage = itemView.findViewById(R.id.weatherImage);
            dateText = itemView.findViewById(R.id.dateText);
        }

    }

    private Intent transition(Context context, ModelWeather model, int position) {
        Intent intent = new Intent(context, WeatherDetailActivity.class);
        intent.putExtra("temp", model.getListWeather().get(position).getMain().getTemp());
        intent.putExtra("desc", model.getListWeather().get(position).getWeather().get(0).getDescription());
        intent.putExtra("wind", model.getListWeather().get(position).getWind().getSpeed());
        intent.putExtra("clouds", model.getListWeather().get(position).getClouds().getClouds());
        intent.putExtra("icon", model.getListWeather().get(position).getWeather().get(0).getIcon());
        intent.putExtra("city", model.getCity().getCity());
        return intent;
    }

}
