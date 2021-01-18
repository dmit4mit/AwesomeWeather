package com.dmitron.data.local

import com.dmitron.data.local.mappers.facade.DatabaseCityWeatherMapperFacade
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityWeatherRoomDataSource(
//    private val mapper: DatabaseCityWeatherMapperFacade,
    private val dao: CityWeatherDao,
) : CityWeatherLocalDataSource {
//    override suspend fun getCityWeather(cityId: Long): Flow<CityWeather> {
//        return dao.getById(cityId).map { mapper.mapDatabaseCityWeather(it) }
//    }

    override suspend fun addCityWeather(cityWeather: CityWeather) {

//        dao.insert()
    }
}