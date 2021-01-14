package com.dmitron.data.remote.mappers

import com.dmitron.data.remote.model.NetworkWeather
import com.dmitron.data.remote.model.NetworkWeather.NetworkDay.NetworkHourlyWeather
import com.dmitron.data.utils.mapNullInputList
import com.dmitron.data.utils.orDefault
import com.dmitron.domain.models.Weather
import com.dmitron.domain.models.Weather.Day.HourlyWeather

internal fun mapNetworkWeather(source: NetworkWeather): Weather =
    Weather(
        days = mapNullInputList(source.days, ::mapNetworkDay),
        id = source.id.orDefault(),
    )

internal fun mapNetworkDay(source: NetworkWeather.NetworkDay): Weather.Day =
    Weather.Day(
        dayOfTheWeek = source.dayOfTheWeek.orDefault(),
        low = source.low.orDefault(),
        high = source.high.orDefault(),
        weatherType = mapWeatherType(source.weatherType.orEmpty()),
        hourlyWeather = mapNullInputList(source.hourlyWeather, ::mapHourlyWeather)
    )

internal fun mapHourlyWeather(source: NetworkHourlyWeather): HourlyWeather =
    HourlyWeather(
        hour = source.hour.orDefault(),
        humidity = source.humidity.orDefault(),
        rainChance = source.rainChance.orDefault(),
        temperature = source.temperature.orDefault(),
        weatherType = mapWeatherType(source.weatherType.orEmpty()),
        windSpeed = source.windSpeed.orDefault(),
    )

internal fun mapWeatherType(weatherType: String) : Weather.WeatherType =
    when(weatherType) {
        "sunny" -> Weather.WeatherType.SUNNY
        else -> Weather.WeatherType.SUNNY
    }