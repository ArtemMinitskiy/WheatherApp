package com.example.artem.wheatherapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artem.wheatherapp.R;
import com.example.artem.wheatherapp.WeatherDetailActivity;
import com.example.artem.wheatherapp.databinding.WeatherItemBinding;
import com.example.artem.wheatherapp.model.WeatherItem;

import java.util.ArrayList;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    private ArrayList<WeatherItem> weatherItems;

    public WeatherRecyclerAdapter(ArrayList<WeatherItem> weatherItems) {
        this.weatherItems = weatherItems;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        WeatherItemBinding binding = WeatherItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final WeatherItem weatherItem = weatherItems.get(position);
        holder.binding.setWeather(weatherItem);

        holder.cardView.setOnClickListener(view ->
                holder.itemView.getContext().startActivity(transition(holder.itemView.getContext(), weatherItems, position)));
    }

    public int getItemCount() {
        return weatherItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        WeatherItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            cardView = itemView.findViewById(R.id.cardview);

        }

    }

    private Intent transition(Context context, ArrayList<WeatherItem> model, int position) {
        Intent intent = new Intent(context, WeatherDetailActivity.class);
        intent.putParcelableArrayListExtra("Weather", model);
        intent.putExtra("position", position);
        return intent;
    }

}
