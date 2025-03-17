package com.cscorner.myweatherapp

data class weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)