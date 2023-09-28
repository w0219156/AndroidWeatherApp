package com.example.weatherapp701.models;

import java.io.Serializable;

public class Weather implements Serializable {

    private Location location;
    private Current current;

    public Weather(Location location, Current current){
        this.location = location;
        this.current = current;
    }

    public Location getLocation() { return location; }
    public Current getCurrent() { return current; }
}
