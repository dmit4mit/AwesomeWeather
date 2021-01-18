package com.dmitron.domain.repository

import com.dmitron.common.ResultWrapper
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import kotlinx.coroutines.flow.Flow

interface CityWeatherRepository {
    suspend fun getAllCities(): Flow<ResultWrapper<List<City>>>
    suspend fun getCityWeathersById(ids: List<Long>): Flow<ResultWrapper<List<CityWeather>>>
    suspend fun getCityWeatherById(cityId: Long): Flow<ResultWrapper<CityWeather>>
    suspend fun searchCities(query: String): Flow<ResultWrapper<List<City>>>
    fun saveCitiesIds(ids: List<Long>)
    fun getSavedCitiesIds(): List<Long>
}