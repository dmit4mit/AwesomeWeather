package com.dmitron.data.remote

import com.dmitron.common.ResultWrapper
import com.dmitron.data.remote.api.WeatherApiService
import com.dmitron.data.remote.mappers.facade.NetworkCityWeatherMapperFacade
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather

class CityWeatherApiDataSource(
    private val weatherApiService: WeatherApiService,
    private val mapper: NetworkCityWeatherMapperFacade,
) : CityWeatherRemoteDataSource {
    override suspend fun getCityWeather(cityId: String): ResultWrapper<CityWeather> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCities(): ResultWrapper<List<City>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchCities(query: String): ResultWrapper<List<City>> {
        TODO("Not yet implemented")
    }
}