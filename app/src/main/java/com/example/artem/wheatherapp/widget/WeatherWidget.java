package com.example.artem.wheatherapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.artem.wheatherapp.MainActivity;
import com.example.artem.wheatherapp.R;
import com.example.artem.wheatherapp.api.RestManager;
import com.example.artem.wheatherapp.api.RestManagerLocation;
import com.example.artem.wheatherapp.model.ModelLocation;
import com.example.artem.wheatherapp.model.ModelWeather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.artem.wheatherapp.adapter.RecyclerAdapter.FormatTemp;

/**
 * Created by Artem on 10.02.2018.
 */

public class WeatherWidget extends AppWidgetProvider {

    private RestManagerLocation restManagerLocation;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int id : appWidgetIds) {
            getLocation(context, appWidgetManager, id);
        }
    }

    private void getLocation(final Context context, final AppWidgetManager appWidgetManager, final int id) {
        restManagerLocation = new RestManagerLocation();
        Call<ModelLocation> call = restManagerLocation.getLocationAPI().getLocation();
        call.enqueue(new Callback<ModelLocation>() {
            @Override
            public void onResponse(Call<ModelLocation> call, Response<ModelLocation> response) {
                Log.d("Log", "onResponse: Server Response: " + response.toString());
                Log.d("Log", "onResponse: received information: " + response.body().toString());
                ModelLocation modelLocation = response.body();
                updateWidget(modelLocation.getLatitude(), modelLocation.getLongitude(), context, appWidgetManager, id);
            }

            @Override
            public void onFailure(Call<ModelLocation> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    static void updateWidget(String latitude, String longitude, final Context context, final AppWidgetManager appWidgetManager, final int widgetID) {

        final RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.weather_widget);

        RestManager restManager = new RestManager();
        Call<ModelWeather> call =  restManager.getWeatherAPI().getWeather(latitude, longitude);

        call.enqueue(new Callback<ModelWeather>() {
            @Override
            public void onResponse(Call<ModelWeather> call, Response<ModelWeather> response) {
                Log.d("Log", "onResponse: Server Response: " + response.toString());
                Log.d("Log", "onResponse: received information: " + response.body().toString());
                ModelWeather modelWeather = response.body();

                widgetView.setTextViewText(R.id.widgetText, FormatTemp(modelWeather.getListWeather().get(0)
                        .getMain().getTemp()));

                appWidgetManager.updateAppWidget(widgetID, widgetView);
            }

            @Override
            public void onFailure(Call<ModelWeather> call, Throwable t) {
                Log.e("Log", "onFailure: Something went wrong: " + t.getMessage() );
            }
        });

    }

}
