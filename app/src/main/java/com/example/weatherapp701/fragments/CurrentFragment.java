package com.example.weatherapp701.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp701.R;

import com.example.weatherapp701.models.Current;
import com.example.weatherapp701.models.Location;
import com.example.weatherapp701.models.Weather;

public class CurrentFragment extends Fragment {
    View view;
    Weather weather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_current, container, false);

        weather = (Weather)getArguments().getSerializable("weather");

        if(weather != null){
            // Display the current temperature
            TextView textViewTemperature = view.findViewById(R.id.textViewTemperature);
            String temperatureVal = String.valueOf(Math.round(weather.getCurrent().getTemperature()));
            String temperatureText = temperatureVal + "°C";
            textViewTemperature.setText(temperatureText);

            // Display the weather description
            TextView textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewDescription.setText(weather.getCurrent().getCondition().getText());

            // Display the current weather icon
            ImageView imageViewIcon = view.findViewById(R.id.imageViewIcon);
            String weatherUrl = weather.getCurrent().getCondition().getIcon();
            weatherUrl = "https:" + weatherUrl.replace("64x64","128x128");

            // Use Glide to load image from API
            Glide.with(view).load(weatherUrl).into(imageViewIcon);
        }

        // Inflate the layout for this fragment
        return view;
    }
}