package com.dmitron.data.local.mappers.facade

import com.dmitron.data.local.model.*
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.models.Weather

class DatabaseCityWeatherMapperFacade(
    val mapDatabaseCityWeather: (DatabaseCityWeather) -> CityWeather,
    val mapCityToDatabase: (City) -> DatabaseCity,
    val mapWeatherToDatabase: (Weather, cityId: Long) -> DatabaseWeather,
    val mapDayToDatabase: (Weather.Day, weatherId: Int) -> DatabaseDay,
    val mapHourToDatabase: (Weather.Day.HourlyWeather, dayId: Long) -> DatabaseHourlyWeather,
)