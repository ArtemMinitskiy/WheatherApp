package com.example.artem.wheatherapp.model.listweather;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.artem.wheatherapp.model.ModelWeather;
import com.example.artem.wheatherapp.model.listweather.weather.Weather;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Artem on 30.01.2018.
 */

public class ListWeather implements Parcelable {

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
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;

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

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    @Override
    public String toString() {
        return "ListWeather{" +
                "main=" + main +
                ", weather=" + weather +
                ", wind=" + wind +
                ", dt_txt='" + dt_txt + '\'' +
                ", clouds=" + clouds +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.main, flags);
        dest.writeList(this.weather);
        dest.writeParcelable(this.wind, flags);
        dest.writeString(this.dt_txt);
        dest.writeParcelable(this.clouds, flags);
    }

    public ListWeather() {
    }

    protected ListWeather(Parcel in) {
        this.main = in.readParcelable(Main.class.getClassLoader());
        this.weather = new ArrayList<Weather>();
        in.readList(this.weather, Weather.class.getClassLoader());
        this.wind = in.readParcelable(Wind.class.getClassLoader());
        this.dt_txt = in.readString();
        this.clouds = in.readParcelable(Clouds.class.getClassLoader());
    }

    public static final Parcelable.Creator<ListWeather> CREATOR = new Parcelable.Creator<ListWeather>() {
        @Override
        public ListWeather createFromParcel(Parcel source) {
            return new ListWeather(source);
        }

        @Override
        public ListWeather[] newArray(int size) {
            return new ListWeather[size];
        }
    };
}
