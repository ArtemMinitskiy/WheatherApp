package com.example.artem.wheatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artem.wheatherapp.adapter.WeatherRecyclerAdapter;
import com.example.artem.wheatherapp.model.listweather.ListWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WeatherDetailActivity extends AppCompatActivity {
    public TextView tempText;
    public TextView descriptionText;
    public TextView windText;
    public TextView cloudsText;
    public TextView cityText;
    public ImageView weatherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        tempText = findViewById(R.id.textTemp);
        descriptionText = findViewById(R.id.textDescription);
        windText = findViewById(R.id.textWind);
        cloudsText = findViewById(R.id.textClouds);
        cityText = findViewById(R.id.textCity);
        weatherImage = findViewById(R.id.weatherImage);

        Intent intent = getIntent();
        ArrayList<ListWeather> listWeathers = intent.getParcelableArrayListExtra("Weather");
        int position = intent.getIntExtra("position" , 0);
        String nameCity = intent.getStringExtra("nameCity");
        getSupportActionBar().setTitle(nameCity);
        Log.i("Log", "" + WeatherRecyclerAdapter.FormatTemp(listWeathers.get(position).getMain().getTemp()));
        Log.i("Log", "" + position);

        tempText.setText(WeatherRecyclerAdapter.FormatTemp(listWeathers.get(position).getMain().getTemp()));
        descriptionText.setText(listWeathers.get(position).getWeather().get(0).getDescription());
        windText.setText(listWeathers.get(position).getWind().getSpeed());
        cloudsText.setText(listWeathers.get(position).getClouds().getClouds());
        cityText.setText(nameCity);
        Picasso.get()
                .load("http://openweathermap.org/img/w/" + listWeathers.get(position).getWeather().get(0).getIcon() + ".png")
                .into(weatherImage);
    }


}
