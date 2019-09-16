package com.example.artem.wheatherapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.artem.wheatherapp.databinding.ActivityWeatherDetailBinding;
import com.example.artem.wheatherapp.model.WeatherItem;

import java.util.ArrayList;

public class WeatherDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWeatherDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather_detail);

        Intent intent = getIntent();

        ArrayList<WeatherItem> listWeathers = intent.getParcelableArrayListExtra("Weather");
        WeatherItem weatherItem = listWeathers.get(intent.getIntExtra("position", 0));
        getSupportActionBar().setTitle(listWeathers.get(0).getCity());

        binding.setWeather(weatherItem);

    }
}
