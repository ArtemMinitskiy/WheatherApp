package com.example.artem.wheatherapp.model.listweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Artem on 30.01.2018.
 */

public class Wind {
    @SerializedName("speed")
    @Expose
    private String speed;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed='" + speed + '\'' +
                '}';
    }
}
