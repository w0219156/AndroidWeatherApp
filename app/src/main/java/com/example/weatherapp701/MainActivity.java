package com.example.weatherapp701;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp701.databinding.ActivityMainBinding;
import com.example.weatherapp701.fragments.CurrentFragment;
import com.example.weatherapp701.fragments.ForecastFragment;
import com.example.weatherapp701.models.Weather;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CurrentFragment currentFragment;
    ForecastFragment forecastFragment;

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

        //
        // Setup bottom navigation
        //

        NavigationBarView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Current fragment selected
                if(itemId == R.id.navigation_current)
                {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, currentFragment)
                            .commit();

                    return true;
                }

                // Forecast fragment selected
                if(itemId == R.id.navigation_forecast){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frameLayout, forecastFragment)
                            .commit();

                    return true;
                }

                return false;
            }
        });

        //
        // Initialize fragments
        //

        currentFragment = new CurrentFragment();
        forecastFragment = new ForecastFragment();

        // Add weather object to Bundle to pass to fragment
        Bundle bundle = new Bundle();
        bundle.putSerializable("weather", weather);

        currentFragment.setArguments(bundle);

        // Display the currentFragment
        bottomNavigationView.setSelectedItemId(R.id.navigation_current);
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

}