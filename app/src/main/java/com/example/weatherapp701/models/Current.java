package com.example.weatherapp701.models;

import com.google.gson.annotations.SerializedName;

public class Current {
    @SerializedName("temp_c")
    private float temperature;

    @SerializedName("wind_kph")
    private float windSpeed;

    @SerializedName("wind_dir")
    private String windDirection;

    @SerializedName("feelslike_c")
    private float feelsLike;

    private Condition condition;

    public Current(float temperature, float windSpeed, String windDirection, float feelsLike, Condition condition){
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.feelsLike = feelsLike;
        this.condition = condition;
    }

    public float getTemperature() { return temperature; }

    public float getWindSpeed() { return windSpeed; }

    public String getWindDirection() { return windDirection; }

    public float getFeelsLike() { return feelsLike; }

    public Condition getCondition() { return condition; }
}
