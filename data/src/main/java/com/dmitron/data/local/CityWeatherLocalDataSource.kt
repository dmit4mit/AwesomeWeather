package com.dmitron.data.local

import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather

interface CityWeatherLocalDataSource {
    suspend fun getCityWeather(cityId: String): CityWeather
    suspend fun getAllCities(): List<City>

    suspend fun addCities(cities: List<City>)
    suspend fun addCityWeather(cityWeather: CityWeather)
}