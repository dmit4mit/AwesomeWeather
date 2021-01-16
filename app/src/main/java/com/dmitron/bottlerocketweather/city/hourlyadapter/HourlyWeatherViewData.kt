package com.dmitron.bottlerocketweather.city.hourlyadapter

import androidx.annotation.DrawableRes
import com.dmitron.bottlerocketweather.utils.DateTimeUtils
import com.dmitron.bottlerocketweather.utils.getDrawable
import com.dmitron.domain.models.Weather

class HourlyWeatherViewData(
    @DrawableRes val icon: Int,
    val time: String,
    val temperature: String,
    val rainChance: String,
    val windSpeed: String,
    val humidity: String,
)

fun Weather.Day.HourlyWeather.toViewData() =
    HourlyWeatherViewData(
        icon = weatherType.getDrawable(),
        time = DateTimeUtils.format24HourTo12(hour),
        temperature = "${temperature}°",
        rainChance = "${rainChance * 100}%",
        windSpeed = windSpeed.toString(),
        humidity = "${humidity * 100}%",
    )