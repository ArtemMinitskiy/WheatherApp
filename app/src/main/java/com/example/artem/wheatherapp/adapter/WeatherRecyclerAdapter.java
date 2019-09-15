package com.example.artem.wheatherapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artem.wheatherapp.R;
import com.example.artem.wheatherapp.model.listweather.ListWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    private List<ArrayList<ListWeather>> weatherList;
    private WeatherClickListener mListener;

    public WeatherRecyclerAdapter(WeatherClickListener listener) {
        weatherList = new ArrayList<>();
        mListener = listener;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArrayList<ListWeather> weather = weatherList.get(position);
        holder.tempText.setText(FormatTemp(weather.get(position).getMain().getTemp()));
        holder.descriptionText.setText(weather.get(position).getWeather().get(0).getDescription());
        holder.dateText.setText(weather.get(position).getDt_txt());
        Picasso.get()
                .load("http://openweathermap.org/img/w/" + weather.get(position).getWeather().get(0).getIcon() + ".png")
                .into(holder.weatherImage);
        getBackground(holder, weather.get(position).getDt_txt());
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
        return weatherList.size();
    }

    public ArrayList<ListWeather> getSelectedWeather(int position) {
        return weatherList.get(position);
    }

    @SuppressLint("DefaultLocale")
    public static String FormatTemp(String temp) {
        float i = Float.parseFloat(temp) - 273;
        return String.format("%.2f", i);

    }

    public void addData(ArrayList<ListWeather> weathers) {
        weatherList.add(weathers);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getLayoutPosition());
        }
    }

}
