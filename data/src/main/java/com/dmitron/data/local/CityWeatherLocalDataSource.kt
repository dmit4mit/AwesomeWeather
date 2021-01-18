package com.dmitron.data.local

import com.dmitron.domain.models.CityWeather
import kotlinx.coroutines.flow.Flow

interface CityWeatherLocalDataSource {
//    suspend fun getCityWeather(cityId: Long): Flow<CityWeather>
    suspend fun addCityWeather(cityWeather: CityWeather)
}