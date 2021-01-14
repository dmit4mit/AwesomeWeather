package com.dmitron.data.remote.mappers

import com.dmitron.data.remote.mappers.facade.*
import com.dmitron.data.remote.model.NetworkCity
import com.dmitron.data.remote.model.NetworkCityList
import com.dmitron.data.remote.model.NetworkCityWeather
import com.dmitron.data.remote.model.NetworkWeather
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.models.Weather
import org.koin.dsl.module

val networkMappersModule = module {
    factory { NetworkCityWeatherMapperFacade(get(), get()) }

    factory { makeNetworkCityListMapper() }
    factory { makeNetworkCityWeatherMapper() }

    factory { makeNetworkCityMapper() }
    factory { makeNetworkWeatherMapper() }
}

internal fun makeNetworkCityListMapper(): (NetworkCityList) -> List<City> = {
    mapNetworkCityList(it)
}

internal fun makeNetworkCityWeatherMapper(): (NetworkCityWeather) -> CityWeather = {
    mapNetworkCityWeather(it)
}

internal fun makeNetworkCityMapper(): (NetworkCity) -> City = {
    mapNetworkCity(it)
}

internal fun makeNetworkWeatherMapper(): (NetworkWeather) -> Weather = {
    mapNetworkWeather(it)
}