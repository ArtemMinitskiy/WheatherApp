package com.example.artem.wheatherapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Artem on 09.02.2018.
 */

public class RestManagerLocation {
    public static final String BASE_URL = "http://ip-api.com/";
    private LocationAPI locationAPI;

    public LocationAPI getLocationAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        locationAPI = retrofit.create(LocationAPI.class);
        return locationAPI;
    }
}
