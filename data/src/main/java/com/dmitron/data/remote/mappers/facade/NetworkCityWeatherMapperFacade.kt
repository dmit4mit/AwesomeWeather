package com.dmitron.data.remote.mappers.facade

import com.dmitron.data.remote.model.NetworkCityList
import com.dmitron.data.remote.model.NetworkCityWeather
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather

class NetworkCityWeatherMapperFacade (
    val networkCityListMapper: (NetworkCityList) -> List<City>,
    val networkCityWeatherMapper: (NetworkCityWeather) -> CityWeather,
)