package com.dmitron.domain.repository

import androidx.paging.DataSource
import com.dmitron.common.ResultWrapper
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface CityWeatherRepository {
    suspend fun getAllCities(): Flow<ResultWrapper<List<City>>>
    suspend fun getCityWeatherById(cityId: Long): Flow<ResultWrapper<CityWeather>>
    suspend fun searchCities(query: String): Flow<ResultWrapper<List<City>>>
    fun searchPagedCities(query: String, scope: CoroutineScope): DataSource.Factory<Int, City>
    fun saveCitiesIds(ids: List<Long>)
    fun getSavedCitiesIds(): List<Long>
}