package com.example.artem.wheatherapp.model.listweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Artem on 29.01.2018.
 */

public class Main {

    @SerializedName("temp")
    @Expose
    private String temp;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp='" + temp + '\'' +
                '}';
    }
}
