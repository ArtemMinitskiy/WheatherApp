package com.example.artem.wheatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import com.example.artem.wheatherapp.adapter.RecyclerAdapter;
import com.example.artem.wheatherapp.api.RestManager;
import com.example.artem.wheatherapp.model.ModelWeather;
import com.example.artem.wheatherapp.model.ListWeather;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RestManager restManager;

    private List<ArrayList<ListWeather>> listItems;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setRecyclerView();

        restManager = new RestManager();

        Call<ModelWeather> call =  restManager.getWeatherAPI().getWeather();

        call.enqueue(new Callback<ModelWeather>() {
            @Override
            public void onResponse(Call<ModelWeather> call, Response<ModelWeather> response) {
                Log.d("Log", "onResponse: Server Response: " + response.toString());
                Log.d("Log", "onResponse: received information: " + response.body().toString());
                ArrayList<ListWeather> listWeathers = response.body().getListWeather();
                for(int i = 0; i < listWeathers.size(); i++){
                    ArrayList<ListWeather> modelWeather = listWeathers;

                    listItems.add(modelWeather);
//                    adapter.addData(modelWeather);


//                    Log.d("Log", "onResponse: \n" +
//                            "Temp: " + listWeathers.get(i).getMain().getTemp() + "\n" +
//                            "Weather: " + listWeathers.get(i).getWeather().get(0).getDescription() + "\n" +
//                            "Icon: " + listWeathers.get(i).getWeather().get(0).getIcon() + "\n" +
//                            "Wind speed: " + listWeathers.get(i).getWind().getSpeed()  + "\n" +
//                            "-------------------------------------------------------------------------\n\n");

                }

            }

            @Override
            public void onFailure(Call<ModelWeather> call, Throwable t) {
                Log.e("Log", "onFailure: Something went wrong: " + t.getMessage() );
            }
        });
    }

    public void setRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItems = new ArrayList<>();
        adapter = new RecyclerAdapter(listItems);
        recyclerView.setAdapter(adapter);
    }
}
