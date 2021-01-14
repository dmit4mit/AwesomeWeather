package com.dmitron.data.local

import com.dmitron.data.local.mappers.facade.DatabaseCityWeatherMapperFacade
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather

class CityWeatherRoomDataSource(
    private val mapper: DatabaseCityWeatherMapperFacade
) : CityWeatherLocalDataSource {
    override suspend fun getCityWeather(cityId: String): CityWeather {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCities(): List<City> {
        TODO("Not yet implemented")
    }

    override suspend fun addCities(cities: List<City>) {
        TODO("Not yet implemented")
    }

    override suspend fun addCityWeather(cityWeather: CityWeather) {
        TODO("Not yet implemented")
    }
}