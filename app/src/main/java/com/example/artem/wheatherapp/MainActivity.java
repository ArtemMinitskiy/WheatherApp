package com.example.artem.wheatherapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artem.wheatherapp.adapter.RecyclerAdapter;
import com.example.artem.wheatherapp.adapter.WeatherClickListener;
import com.example.artem.wheatherapp.api.RestManager;
import com.example.artem.wheatherapp.model.ModelWeather;
import com.example.artem.wheatherapp.model.listweather.ListWeather;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.artem.wheatherapp.GpsUtils.GPS_REQUEST;

public class MainActivity extends AppCompatActivity implements WeatherClickListener {
    private RestManager restManager;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ArrayList<ListWeather> modelWeather;

    private String nameCity;

    public static final int LOCATION_REQUEST = 1000;

    private FusedLocationProviderClient mFusedLocationClient;

    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean isContinue = false;
    private boolean isGPS = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setRecyclerView();
        setLocationRequest();

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                isGPS = isGPSEnable;
            }
        });

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };

        getLocation();

    }

    private void setLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
    }

    private void getWeather(final Double lat, final Double lon) {
        restManager = new RestManager();

        Call<ModelWeather> call =  restManager.getWeatherAPI().getWeather(String.valueOf(lat), String.valueOf(lon));
        call.enqueue(new Callback<ModelWeather>() {
            @Override
            public void onResponse(@NonNull Call<ModelWeather> call, @NonNull Response<ModelWeather> response) {
                Log.d("Log", "onResponse: Server Response: " + response.toString());
                Log.d("Log", "onResponse: received information: " + response.body().toString());

                nameCity = response.body().getCity().getCity();
                getSupportActionBar().setTitle(nameCity);

                ArrayList<ListWeather> listWeathers = response.body().getListWeather();
                for(int i = 0; i < listWeathers.size(); i++){
                    modelWeather = listWeathers;

                    adapter.addData(modelWeather);

                    Log.d("Log", lat + " Latitude " + lon + " Longtitude");

                    Log.d("Log", "onResponse: \n" +
                            "Temp: " + listWeathers.get(0).getMain().getTemp() + "\n" +
                            "Weather: " + listWeathers.get(0).getWeather().get(0).getDescription() + "\n" +
                            "Icon: " + listWeathers.get(0).getWeather().get(0).getIcon() + "\n" +
                            "Wind speed: " + listWeathers.get(0).getWind().getSpeed()  + "\n" +
                            "City: " + response.body().getCity().getCity()  + "\n" +
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
        intent.putExtra("nameCity", nameCity);
        startActivity(intent);
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST);

        } else {
            if (isContinue) {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            } else {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            wayLatitude = location.getLatitude();
                            wayLongitude = location.getLongitude();
                            getWeather(wayLatitude, wayLongitude);
                        } else {
                            mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                        }
                    }

                });
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isContinue) {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    } else {
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    wayLatitude = location.getLatitude();
                                    wayLongitude = location.getLongitude();
                                } else {
                                    mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_REQUEST) {
                isGPS = true;
            }
        }
    }
}
