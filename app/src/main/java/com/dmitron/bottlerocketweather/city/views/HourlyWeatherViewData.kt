package com.dmitron.bottlerocketweather.city.views

import androidx.annotation.DrawableRes

class HourlyWeatherViewData(
    @DrawableRes val icon: Int,
    val time: String,
    val temperature: String,
    val rainChance: String,
    val windSpeed: String,
    val humidity: String,
)