package com.example.artem.wheatherapp.model;

public class WeatherItem {
    private String city;
    private String temp;
    private String desc;
    private String icon;
    private String date;

    public WeatherItem(String city, String temp, String desc, String icon, String date) {
        this.city = city;
        this.temp = temp;
        this.desc = desc;
        this.icon = icon;
        this.date = date;
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
}
