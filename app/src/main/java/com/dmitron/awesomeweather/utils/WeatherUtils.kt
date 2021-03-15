package com.dmitron.awesomeweather.utils

import androidx.annotation.DrawableRes
import com.dmitron.awesomeweather.R
import com.dmitron.domain.models.Weather

@DrawableRes
fun Weather.WeatherType.getDrawable(): Int = when(this) {
    Weather.WeatherType.SUNNY -> R.drawable.ic_sunny_active
    Weather.WeatherType.CLOUDY -> R.drawable.ic_cloudy_active
    Weather.WeatherType.LIGHT_RAIN -> R.drawable.ic_light_rain_active
    Weather.WeatherType.PARTLY_CLOUDY -> R.drawable.ic_partly_cloudy_active
    Weather.WeatherType.HEAVY_RAIN -> R.drawable.ic_heavy_rain_active
    Weather.WeatherType.SNOW_SLEET -> R.drawable.ic_snow_sleet_active
}