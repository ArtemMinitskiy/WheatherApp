package com.example.artem.wheatherapp.api;

import com.example.artem.wheatherapp.model.ModelWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Artem on 29.01.2018.
 */

public interface WeatherAPI {

    @GET("/data/2.5/forecast?&appid=811938537aa2fa23ff4838a05e5ae044")
    Observable<ModelWeather> getWeather(@Query("lat") String latitude, @Query("lon") String longitude);
}
