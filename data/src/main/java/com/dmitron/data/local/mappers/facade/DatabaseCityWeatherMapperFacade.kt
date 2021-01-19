package com.dmitron.data.local.mappers.facade

import com.dmitron.data.local.model.*
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.models.Weather

class DatabaseCityWeatherMapperFacade(
    val mapDatabaseCityWeather: (DatabaseCityWeather) -> CityWeather,
    val mapCityToDatabase: (City) -> DatabaseCity,
    val mapWeatherToDatabase: (Weather, cityId: Long) -> DatabaseWeather,
    val mapDayToDatabase: (Weather.Day, weatherId: Int, cityId: Long) -> DatabaseDay,
    val mapHourToDatabase: (
        Weather.Day.HourlyWeather, dayId: Long, cityId: Long,
        parentDayOfWeek: Int,
        parentWeatherId: Int
    ) -> DatabaseHourlyWeather,
)