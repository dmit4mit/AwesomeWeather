package com.dmitron.data.remote

import android.content.Context
import com.dmitron.common.ResultWrapper
import com.dmitron.data.remote.api.WeatherApiService
import com.dmitron.data.remote.mappers.facade.NetworkCityWeatherMapperFacade
import com.dmitron.data.utils.request
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather

class CityWeatherApiDataSource(
    private val context: Context,
    private val weatherApiService: WeatherApiService,
    private val mapper: NetworkCityWeatherMapperFacade,
) : CityWeatherRemoteDataSource {
    override suspend fun getCityWeather(cityId: Long): ResultWrapper<CityWeather> {
        return request(context) { weatherApiService.getCityWeather(cityId) }
            .map(mapper.networkCityWeatherMapper)
    }

    override suspend fun getAllCities(): ResultWrapper<List<City>> {
        return request(context) { weatherApiService.getCityList() }
            .map(mapper.networkCityListMapper)
    }

    override suspend fun searchCities(query: String): ResultWrapper<List<City>> {
        return request(context) { weatherApiService.getCityList(query) }
            .map(mapper.networkCityListMapper)
    }

    override suspend fun searchCities(
        query: String,
        pageCount: Int,
        pageNumber: Int
    ): ResultWrapper<List<City>> {
        return request(context) { weatherApiService.getCityList(query, pageCount, pageNumber) }
            .map(mapper.networkCityListMapper)
    }
}