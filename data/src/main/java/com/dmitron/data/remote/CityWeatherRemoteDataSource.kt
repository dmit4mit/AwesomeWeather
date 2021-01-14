package com.dmitron.data.remote

import com.dmitron.common.ResultWrapper
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather

interface CityWeatherRemoteDataSource {
    suspend fun getCityWeather(cityId: String): ResultWrapper<CityWeather>
    suspend fun getAllCities(): ResultWrapper<List<City>>
    suspend fun searchCities(query: String): ResultWrapper<List<City>>
}