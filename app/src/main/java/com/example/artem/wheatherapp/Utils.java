package com.example.artem.wheatherapp;

import android.annotation.SuppressLint;

public class Utils {

    public static final String BASE_URL = "http://api.openweathermap.org/";

    public static final int NUMBER_OF_LISTWEATHER = 35;

    @SuppressLint("DefaultLocale")
    public static String FormatTemp(String temp) {
        float i = Float.parseFloat(temp) - 273;
        return String.format("%.2f", i);

    }
}
