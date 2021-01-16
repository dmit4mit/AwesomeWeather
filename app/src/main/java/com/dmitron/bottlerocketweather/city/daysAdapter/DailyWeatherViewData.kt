package com.dmitron.bottlerocketweather.city.daysAdapter

import androidx.annotation.DrawableRes
import com.dmitron.bottlerocketweather.utils.DateTimeUtils
import com.dmitron.bottlerocketweather.utils.getDrawable
import com.dmitron.domain.models.Weather

data class DailyWeatherViewData(
    val dayOfTheWeekNumber: Int,
    val dayOfTheWeekDisplay: String,
    @DrawableRes val weatherIcon: Int,
    val temperature: String,
    val isChecked: Boolean,
)

fun Weather.Day.toViewData(isChecked: Boolean) =
    DailyWeatherViewData(
        dayOfTheWeekNumber = dayOfTheWeek,
        dayOfTheWeekDisplay = DateTimeUtils.formatDayOfWeekToString(dayOfTheWeek),
        weatherIcon = weatherType.getDrawable(),
        temperature = arrayOf(low, high).average().toInt().toString(),
        isChecked = isChecked,
    )