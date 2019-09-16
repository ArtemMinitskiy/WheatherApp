package com.example.artem.wheatherapp.binding;

import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.databinding.BindingAdapter;

import com.example.artem.wheatherapp.R;
import com.squareup.picasso.Picasso;

public class BindingUtils {

    @BindingAdapter({"setBackground"})
    public static void setCardViewBackground(CardView cardView, String date) {
        switch (date) {
            case "12:00:00":
            case "15:00:00":
                cardView.setBackgroundResource(R.drawable.daymini);
                break;
            case "18:00:00":
            case "21:00:00":
                cardView.setBackgroundResource(R.drawable.sunsetmini);
                break;
            case "00:00:00":
            case "03:00:00":
                cardView.setBackgroundResource(R.drawable.nightmini);
                break;
            case "06:00:00":
            case "09:00:00":
                cardView.setBackgroundResource(R.drawable.sunrisemini);
                break;
        }

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load("http://openweathermap.org/img/w/" + imageUrl + ".png").into(imageView);
    }


}
