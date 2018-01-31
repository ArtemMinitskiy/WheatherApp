package com.example.artem.wheatherapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem on 29.01.2018.
 */

public class RestManager {

    private static final String BASE_URL = "http://samples.openweathermap.org/";
    private WeatherAPI weatherAPI;

    public WeatherAPI getWeatherAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherAPI = retrofit.create(WeatherAPI.class);
        return weatherAPI;
    }

}
