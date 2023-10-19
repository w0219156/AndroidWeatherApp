package com.example.weatherapp701.models;

import java.io.Serializable;

public class Weather implements Serializable {
    private Location location;
    private Current current;
    private Forecast forecast;  // Add this line to include the Forecast property

    public Weather(Location location, Current current, Forecast forecast) {  // Update constructor to accept Forecast
        this.location = location;
        this.current = current;
        this.forecast = forecast;  // Initialize the Forecast property
    }

    public Location getLocation() { return location; }
    public Current getCurrent() { return current; }
    public Forecast getForecast() { return forecast; }  // Getter for Forecast property
}
