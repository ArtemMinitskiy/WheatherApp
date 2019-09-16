package com.example.artem.wheatherapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherItem implements Parcelable {
    private String city;
    private String temp;
    private String desc;
    private String icon;
    private String date;
    private String wind;
    private String clouds;

    public WeatherItem(String city, String temp, String desc, String icon, String date, String wind, String clouds) {
        this.city = city;
        this.temp = temp;
        this.desc = desc;
        this.icon = icon;
        this.date = date;
        this.wind = wind;
        this.clouds = clouds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.city);
        parcel.writeString(this.temp);
        parcel.writeString(this.desc);
        parcel.writeString(this.icon);
        parcel.writeString(this.date);
        parcel.writeString(this.wind);
        parcel.writeString(this.clouds);
    }

    protected WeatherItem(Parcel in) {
        this.city = in.readString();
        this.temp = in.readString();
        this.desc = in.readString();
        this.icon = in.readString();
        this.date = in.readString();
        this.wind = in.readString();
        this.clouds = in.readString();

    }

    public static final Parcelable.Creator<WeatherItem> CREATOR = new Parcelable.Creator<WeatherItem>() {
        @Override
        public WeatherItem createFromParcel(Parcel source) {
            return new WeatherItem(source);
        }

        @Override
        public WeatherItem[] newArray(int size) {
            return new WeatherItem[size];
        }
    };
}
