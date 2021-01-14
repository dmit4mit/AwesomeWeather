package com.dmitron.data.local.mappers.facade

import com.dmitron.data.local.model.DatabaseCityWeather
import com.dmitron.data.remote.model.NetworkCityWeather

class DatabaseCityWeatherMapperFacade(
    val mapNetworkCityWeather: (NetworkCityWeather) -> DatabaseCityWeather,
)