package com.dmitron.data.local

import com.dmitron.data.local.mappers.facade.DatabaseCityWeatherMapperFacade
import com.dmitron.domain.models.CityWeather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityWeatherRoomDataSource(
    private val mapper: DatabaseCityWeatherMapperFacade,
    private val dao: CityWeatherDao,
) : CityWeatherLocalDataSource {
    override suspend fun getCityWeather(cityId: Long): Flow<CityWeather> {
        return dao.getById(cityId).map { mapper.mapDatabaseCityWeather(it) }
    }

    override suspend fun addCityWeather(cityWeather: CityWeather) {
        dao.insertCity(mapper.mapCityToDatabase(cityWeather.city))
        dao.insertWeather(mapper.mapWeatherToDatabase(cityWeather.weather, cityWeather.city.id))

        cityWeather.weather.days.forEach { day ->
            val databaseDay = mapper.mapDayToDatabase(day, cityWeather.weather.id)
            val dayId = dao.insertDay(databaseDay)
            day.hourlyWeather.forEach { hour ->
                val hourDatabase = mapper.mapHourToDatabase(hour, dayId)
                dao.insertHour(hourDatabase)
            }
        }
    }
}