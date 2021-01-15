package com.dmitron.data.remote.mappers

import com.dmitron.data.remote.mappers.facade.NetworkCityWeatherMapperFacade
import com.dmitron.data.remote.model.NetworkCity
import com.dmitron.data.remote.model.NetworkCityList
import com.dmitron.data.remote.model.NetworkCityWeather
import com.dmitron.data.remote.model.NetworkWeather
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.models.Weather
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val NETWORK_CITY_WEATHER_MAPPER = "NETWORK_CITY_WEATHER_MAPPER"
private const val NETWORK_CITY_LIST_MAPPER = "NETWORK_CITY_LIST_MAPPER"
private const val NETWORK_CITY_MAPPER = "NETWORK_CITY_MAPPER"
private const val NETWORK_WEATHER_MAPPER = "NETWORK_WEATHER_MAPPER"

val networkMappersModule = module {
    factory {
        NetworkCityWeatherMapperFacade(
            get(named(NETWORK_CITY_LIST_MAPPER)),
            get(named(NETWORK_CITY_WEATHER_MAPPER)),
        )
    }

    factory(named(NETWORK_CITY_LIST_MAPPER)) { makeNetworkCityListMapper() }
    factory(named(NETWORK_CITY_WEATHER_MAPPER)) { makeNetworkCityWeatherMapper() }

    factory(named(NETWORK_CITY_MAPPER)) { makeNetworkCityMapper() }
    factory(named(NETWORK_WEATHER_MAPPER)) { makeNetworkWeatherMapper() }
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