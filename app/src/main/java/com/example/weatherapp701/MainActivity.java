package com.example.weatherapp701;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp701.databinding.ActivityMainBinding;
import com.example.weatherapp701.models.Weather;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String json = getJsonFromFile();

        Gson gson = new Gson();
        Weather weather = gson.fromJson(json, Weather.class);

        // Display the location
        TextView textViewLocation = binding.textViewLocation;
        textViewLocation.setText(weather.getLocation().getName());

        // Display the current temperature
        TextView textViewTemperature = binding.textViewTemperature;
        String temperatureVal = getFloatAsString(weather.getCurrent().getTemperature());
        String temperatureText = String.valueOf(temperatureVal) + "°C";
        textViewTemperature.setText(temperatureText);

        // Display the weather description
        TextView textViewDescription = binding.textViewDescription;
        textViewDescription.setText(weather.getCurrent().getCondition().getText());

        // Display the current weather icon
        ImageView imageViewIcon = binding.imageViewIcon;
        String weatherUrl = weather.getCurrent().getCondition().getIcon();
        weatherUrl = "https:" + weatherUrl.replace("64x64","128x128");

        // Use Glide to load image from API
        Glide.with(view).load(weatherUrl).into(imageViewIcon);
    }

    private String getJsonFromFile() {
        String json = "";

        InputStream inputStream = this.getResources().openRawResource(R.raw.weather_api);

        // Create InputStreamReader object
        InputStreamReader isReader = new InputStreamReader(inputStream);

        // Create a BufferedReader object
        BufferedReader reader = new BufferedReader(isReader);

        // Read the buffer and save to string
        json = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        return json;
    }

    private String getFloatAsString(float num){
        return BigDecimal.valueOf(num).setScale(0, BigDecimal.ROUND_HALF_UP).toString();
    }
}