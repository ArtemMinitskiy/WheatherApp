package com.example.artem.wheatherapp.model.listweather;

import com.example.artem.wheatherapp.model.ModelWeather;
import com.example.artem.wheatherapp.model.listweather.weather.Weather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Artem on 30.01.2018.
 */

public class ListWeather extends ModelWeather {

    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private ArrayList<Weather> weather;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("dt_txt")
    @Expose
    private String dt_txt;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return "ListWeather{" +
                "main=" + main +
                ", weather=" + weather +
                ", wind=" + wind +
                ", dt_txt=" + dt_txt +
                '}';
    }
}
