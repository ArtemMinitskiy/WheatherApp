package com.example.artem.wheatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artem.wheatherapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Artem on 30.01.2018.
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.tempText)
    public TextView tempText;
    @BindView(R.id.descriptionText)
    public TextView descriptionText;
    @BindView(R.id.weatherImage)
    public ImageView weatherImage;
    @BindView(R.id.dateText)
    public TextView dateText;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
