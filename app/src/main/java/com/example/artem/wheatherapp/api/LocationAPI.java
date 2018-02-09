package com.example.artem.wheatherapp.api;

import com.example.artem.wheatherapp.model.ModelLocation;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Artem on 09.02.2018.
 */

public interface LocationAPI {
    @GET("/json")
    Call<ModelLocation> getLocation();
}
