package com.example.artem.wheatherapp;

import com.example.artem.wheatherapp.model.WeatherModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Artem on 29.01.2018.
 */

public interface WeatherAPI {

    @GET("/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22")
    Call<WeatherModel> getWeather();
}
