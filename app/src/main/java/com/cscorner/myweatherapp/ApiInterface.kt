package com.cscorner.myweatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("forecast.json")
    fun myweatherdata(@Query("key") apikey: String,
                      @Query("q") location: String?,
                      @Query("days") days: Int,
                      @Query("aqi") aqi: String,
                      @Query("alerts") alerts: String
    ): Call<weather>

}
