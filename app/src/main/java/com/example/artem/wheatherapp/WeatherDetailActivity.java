package com.example.artem.wheatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.artem.wheatherapp.adapter.WeatherRecyclerAdapter;
import com.squareup.picasso.Picasso;

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

        getSupportActionBar().setTitle(intent.getStringExtra("city"));

        tempText.setText(WeatherRecyclerAdapter.FormatTemp(intent.getStringExtra("temp")));
        descriptionText.setText(intent.getStringExtra("desc"));
        windText.setText(intent.getStringExtra("wind"));
        cloudsText.setText(intent.getStringExtra("clouds"));
        cityText.setText(intent.getStringExtra("city"));
        Picasso.get()
                .load("http://openweathermap.org/img/w/" + intent.getStringExtra("icon") + ".png")
                .into(weatherImage);
    }


}
