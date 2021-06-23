package com.dmitron.data.remote.mappers

import com.dmitron.data.remote.model.NetworkCity
import com.dmitron.data.remote.model.NetworkCityWeather
import com.dmitron.data.remote.model.NetworkWeather
import com.dmitron.domain.models.CityWeather

internal fun mapNetworkCityWeather(source: NetworkCityWeather): CityWeather =
    CityWeather(
        city = mapNetworkCity(source.city ?: NetworkCity()),
        weather = mapNetworkWeather(source.weather ?: NetworkWeather())
    )