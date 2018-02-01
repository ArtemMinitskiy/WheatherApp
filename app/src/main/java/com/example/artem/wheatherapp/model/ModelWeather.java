package com.example.artem.wheatherapp.model;

import com.example.artem.wheatherapp.model.listweather.ListWeather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Artem on 29.01.2018.
 */

public class ModelWeather {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cnt")
    @Expose
    private String cnt;
    @SerializedName("list")
    @Expose
    private ArrayList<ListWeather> listWeather;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCnt() {
        return cnt;
    }

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }

    public ArrayList<ListWeather> getListWeather() {
        return listWeather;
    }

    public void setListWeather(ArrayList<ListWeather> listWeather) {
        this.listWeather = listWeather;
    }

    @Override
    public String toString() {
        return "ModelWeather{" +
                "cod='" + cod + '\'' +
                ", message='" + message + '\'' +
                ", cnt='" + cnt + '\'' +
                ", listWeather=" + listWeather +
                '}';
    }
}
