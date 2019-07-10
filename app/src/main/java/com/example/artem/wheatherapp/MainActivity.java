package com.example.artem.wheatherapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.example.artem.wheatherapp.adapter.RecyclerAdapter;
import com.example.artem.wheatherapp.adapter.WeatherClickListener;
import com.example.artem.wheatherapp.api.RestManager;
import com.example.artem.wheatherapp.model.ModelWeather;
import com.example.artem.wheatherapp.model.listweather.ListWeather;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements WeatherClickListener {
    private RestManager restManager;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<ListWeather> modelWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setRecyclerView();
        getWeather();

    }

    private void getWeather() {
        restManager = new RestManager();

        Call<ModelWeather> call =  restManager.getWeatherAPI().getWeather();
        call.enqueue(new Callback<ModelWeather>() {
            @Override
            public void onResponse(@NonNull Call<ModelWeather> call, @NonNull Response<ModelWeather> response) {
                Log.d("Log", "onResponse: Server Response: " + response.toString());
                Log.d("Log", "onResponse: received information: " + response.body().toString());
                ArrayList<ListWeather> listWeathers = response.body().getListWeather();
                for(int i = 0; i < listWeathers.size(); i++){
                    modelWeather = listWeathers;

                    adapter.addData(modelWeather);

                    Log.d("Log", "onResponse: \n" +
                            "Temp: " + listWeathers.get(0).getMain().getTemp() + "\n" +
                            "Weather: " + listWeathers.get(0).getWeather().get(0).getDescription() + "\n" +
                            "Icon: " + listWeathers.get(0).getWeather().get(0).getIcon() + "\n" +
                            "Wind speed: " + listWeathers.get(0).getWind().getSpeed()  + "\n" +
                            "City: " + response.body().getCity()  + "\n" +
                            "-------------------------------------------------------------------------\n\n");

                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelWeather> call, @NonNull Throwable t) {
                Log.e("Log", "onFailure: Something went wrong: " + t.getMessage() );
            }
        });
    }

    public void setRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new RecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(int position) {
        ArrayList<ListWeather> selectedWeather = adapter.getSelectedWeather(position);
        Intent intent = new Intent(MainActivity.this, WeatherDetailActivity.class);
        intent.putParcelableArrayListExtra("Weather",  selectedWeather);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
