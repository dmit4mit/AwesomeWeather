package com.dmitron.bottlerocketweather.city.views

import androidx.annotation.DrawableRes

data class CompactWeatherViewData(
    val dayOfTheWeek: String,
    @DrawableRes val weatherIcon: Int,
    val temperature: String,
    val isChecked: Boolean,
)