package com.dmitron.data.local

import com.dmitron.common.ResultWrapper
import com.dmitron.domain.models.CityWeather

interface CityWeatherLocalDataSource {
    suspend fun getCityWeather(cityId: Long): ResultWrapper<CityWeather>
    suspend fun addCityWeather(cityWeather: CityWeather)
}