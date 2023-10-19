package com.example.weatherapp701.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.weatherapp701.R;
import com.example.weatherapp701.models.ForecastDay;
import com.example.weatherapp701.models.Weather;

import java.util.List;

public class ForecastFragment extends Fragment {

    private Weather weather;
    private TextView textViewLocation;
    private LinearLayout forecastContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        textViewLocation = view.findViewById(R.id.textViewLocation);
        //forecastContainer = view.findViewById(R.id.forecastContainer);

        // Ensure getArguments() is not null before attempting to get the serializable
        if (getArguments() != null) {
            weather = (Weather) getArguments().getSerializable("weather");
        }

        if (weather != null) {
            textViewLocation.setText(weather.getLocation().getName());
            populateForecast(weather.getForecast().getForecastday());
        }

        return view;
    }

    private void populateForecast(List<ForecastDay> forecastDays) {
        if (forecastDays != null && forecastDays.size() >= 3) {
            setForecastData(forecastDays.get(0), getView().findViewById(R.id.day1));
            setForecastData(forecastDays.get(1), getView().findViewById(R.id.day2));
            setForecastData(forecastDays.get(2), getView().findViewById(R.id.day3));
        }
    }

    private void setForecastData(ForecastDay forecastDay, View dayView) {
        // Extracting the views from the provided dayView
        TextView date = dayView.findViewById(R.id.textViewDate1);
        ImageView icon = dayView.findViewById(R.id.imageViewIcon);
        TextView tempRange = dayView.findViewById(R.id.textViewTempRangeDay1);
        TextView description = dayView.findViewById(R.id.textViewDescriptionDay1);

        // Setting data from the ForecastDay object to the views
        date.setText(forecastDay.getDate());

        Glide.with(this).load("https:" + forecastDay.getDay().getCondition().getIcon()).into(icon);

        String tempText = forecastDay.getDay().getMaxtemp_c() + "°C / " + forecastDay.getDay().getMintemp_c() + "°C";
        tempRange.setText(tempText);

        description.setText(forecastDay.getDay().getCondition().getText());
    }
}
