package com.dmitron.data.local.mappers

import com.dmitron.data.local.model.DatabaseCity
import com.dmitron.data.local.model.DatabaseDay
import com.dmitron.data.local.model.DatabaseHourlyWeather
import com.dmitron.data.local.model.DatabaseWeather
import com.dmitron.domain.models.City
import com.dmitron.domain.models.Weather

internal fun mapHourToDatabase(
    source: Weather.Day.HourlyWeather,
    dayId: Long,
    cityId: Long,
    parentDayOfWeek: Int,
    parentWeatherId: Int
): DatabaseHourlyWeather =
    DatabaseHourlyWeather(
        cityId = cityId,
        parentDayOfWeek = parentDayOfWeek,
        parentWeatherId = parentWeatherId,
        parentDayId = dayId,
        hour = source.hour,
        humidity = source.humidity,
        rainChance = source.rainChance,
        temperature = source.temperature,
        weatherType = source.weatherType.name,
        windSpeed = source.windSpeed,
    )

internal fun mapDayToDatabase(source: Weather.Day, weatherId: Int, cityId: Long): DatabaseDay =
    DatabaseDay(
        cityId = cityId,
        parentWeatherId = weatherId,
        dayOfTheWeek = source.dayOfTheWeek,
        low = source.low,
        high = source.high,
        weatherType = source.weatherType.title
    )

internal fun mapWeatherToDatabase(source: Weather, cityId: Long): DatabaseWeather =
    DatabaseWeather(
        id = source.id,
        associatedCityId = cityId
    )

internal fun mapCityToDatabase(source: City): DatabaseCity =
    DatabaseCity(
        geoNameId = source.id,
        name = source.name,
        countryCode = source.countryCode,
        alternateNames = source.alternateNames,
        imageURLs = mapImageUrlsToDatabase(source.imageURLs),
        latitude = source.latitude,
        longitude = source.longitude,
        timezone = source.timezone,
        modificationDate = source.modificationDate,
        population = source.population
    )

internal fun mapImageUrlsToDatabase(source: City.ImageURLs): DatabaseCity.DatabaseImageURLs =
    DatabaseCity.DatabaseImageURLs(
        hdpiImageURL = source.hdpiImageURL,
        mdpiImageURL = source.mdpiImageURL,
        xhdpiImageURL = source.xhdpiImageURL
    )