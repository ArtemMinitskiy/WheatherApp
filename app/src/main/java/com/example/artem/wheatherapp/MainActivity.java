package com.example.artem.wheatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.artem.wheatherapp.model.WeatherModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RestManager restManager;
    @BindView(R.id.temp)
    TextView tempText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        restManager = new RestManager();

        Call<WeatherModel> call =  restManager.getWeatherAPI().getWeather();

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                Log.d("Log", "onResponse: Server Response: " + response.toString());
                Log.d("Log", "onResponse: received information: " + response.body().toString());
                String temp = response.body().getMain().getTemp();
                Log.d("Log", "onResponse: Temp: " + temp);

                tempText.setText(temp);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.e("Log", "onFailure: Something went wrong: " + t.getMessage() );
            }
        });
    }
}
