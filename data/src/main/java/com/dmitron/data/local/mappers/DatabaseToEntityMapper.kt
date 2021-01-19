package com.dmitron.data.local.mappers

import com.dmitron.data.local.model.DatabaseCity
import com.dmitron.data.local.model.DatabaseCityWeather
import com.dmitron.data.local.model.DatabaseDayWithHours
import com.dmitron.data.local.model.DatabaseHourlyWeather
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.models.Weather

internal fun mapDatabaseCityWeather(source: DatabaseCityWeather): CityWeather =
    CityWeather(
        city = mapDatabaseCity(source.city),
        weather = Weather(
            days = source.weather?.days?.map { mapDatabaseDay(it) }.orEmpty(),
            id = 0
        )
    )

internal fun mapDatabaseDay(source: DatabaseDayWithHours) =
    Weather.Day(
        dayOfTheWeek = source.day.dayOfTheWeek,
        low = source.day.low,
        high = source.day.high,
        weatherType = Weather.WeatherType.fromName(source.day.weatherType),
        hourlyWeather = source.hourlyWeather.map { mapDatabaseHour(it) }
    )

internal fun mapDatabaseHour(source: DatabaseHourlyWeather) =
    Weather.Day.HourlyWeather(
        hour = source.hour,
        humidity = source.humidity,
        rainChance = source.rainChance,
        temperature = source.temperature,
        weatherType = Weather.WeatherType.fromName(source.weatherType),
        windSpeed = source.windSpeed
    )

internal fun mapDatabaseCity(source: DatabaseCity): City =
    City(
        id = source.geoNameId,
        name = source.name,
        countryCode = source.countryCode,
        imageURLs = City.ImageURLs(
            hdpiImageURL = source.imageURLs.hdpiImageURL,
            mdpiImageURL = source.imageURLs.mdpiImageURL,
            xhdpiImageURL = source.imageURLs.xhdpiImageURL
        ),
        alternateNames = source.alternateNames,
        latitude = source.latitude,
        longitude = source.longitude,
        timezone = source.timezone,
        modificationDate = source.modificationDate,
        population = source.population
    )
