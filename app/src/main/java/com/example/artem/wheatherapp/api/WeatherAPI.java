package com.example.artem.wheatherapp.api;

import com.example.artem.wheatherapp.model.ModelWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Artem on 29.01.2018.
 */

public interface WeatherAPI {

//    @GET("/data/2.5/weather?q=Odessa,UA&appid=811938537aa2fa23ff4838a05e5ae044")
//    @GET("/data/2.5/forecast?q=Odessa,UA&appid=811938537aa2fa23ff4838a05e5ae044")
//    Call<ModelWeather> getWeather();

    @GET("/data/2.5/forecast?&appid=811938537aa2fa23ff4838a05e5ae044")
    Call<ModelWeather> getWeather(@Query("lat") String latitude, @Query("lon") String longitude);
}
