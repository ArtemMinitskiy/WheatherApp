package com.example.artem.wheatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artem.wheatherapp.model.listweather.ListWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherDetailActivity extends AppCompatActivity {
    @BindView(R.id.textTemp)
    public TextView tempText;
    @BindView(R.id.textDescription)
    public TextView descriptionText;
    @BindView(R.id.textWind)
    public TextView windText;
    @BindView(R.id.textClouds)
    public TextView cloudsText;
    @BindView(R.id.textCity)
    public TextView cityText;
    @BindView(R.id.weatherImage)
    public ImageView weatherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<ListWeather> listWeathers = intent.getParcelableArrayListExtra("Weather");
        Log.d("Log", "" + listWeathers.get(0));

        tempText.setText(listWeathers.get(0).getMain().getTemp());
        descriptionText.setText(listWeathers.get(0).getWeather().get(0).getDescription());
        windText.setText(listWeathers.get(0).getWind().getSpeed());
        cloudsText.setText(listWeathers.get(0).getClouds().getClouds());
        cityText.setText("Odessa");
        Picasso.with(getApplicationContext())
                .load("http://openweathermap.org/img/w/" + listWeathers.get(0).getWeather().get(0).getIcon() + ".png")
                .into(weatherImage);
    }


}
