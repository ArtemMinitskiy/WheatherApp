package com.example.artem.wheatherapp.api;

import com.example.artem.wheatherapp.model.ModelWeather;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Artem on 29.01.2018.
 */

public interface WeatherAPI {

//    @GET("/data/2.5/weather?q=Odessa,UA&appid=811938537aa2fa23ff4838a05e5ae044")
    @GET("/data/2.5/forecast?q=London,us&appid=b6907d289e10d714a6e88b30761fae22")
    Call<ModelWeather> getWeather();
}
