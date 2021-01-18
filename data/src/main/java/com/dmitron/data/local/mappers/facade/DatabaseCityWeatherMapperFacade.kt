package com.dmitron.data.local.mappers.facade

import com.dmitron.data.local.model.DatabaseCityWeather
import com.dmitron.domain.models.CityWeather

class DatabaseCityWeatherMapperFacade(
    val mapCityWeatherToDatabase: (CityWeather) -> DatabaseCityWeather,
    val mapDatabaseCityWeather: (DatabaseCityWeather) -> CityWeather
)