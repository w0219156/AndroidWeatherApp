package com.example.weatherapp701.retrofit;

import com.example.weatherapp701.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitApi {
    String BASE_URL = "https://api.weatherapi.com";

    @GET("v1/forecast.json")
    Call<Weather> getWeather(@Query("key") String key,
                             @Query("q") String q,
                             @Query("days") String days,
                             @Query("aqi") String aqi,
                             @Query("alerts") String alerts);
    // https://api.weatherapi.com/v1/forecast.json?key=62c7fc37d6b04ecaa6d225851231108&q=B3K 2T3&days=3&aqi=no&alerts=no


    /*@GET("v1/current.json")
    Call<Weather> getCurrent(@Query("key") String key,
                             @Query("q") String q,
                             @Query("aqi") String aqi);*/
    //https://api.weatherapi.com/v1/current.json?key=62c7fc37d6b04ecaa6d225851231108&q=B3K 2T3&aqi=no

}
