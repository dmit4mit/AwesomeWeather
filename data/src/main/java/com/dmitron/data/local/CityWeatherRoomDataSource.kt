package com.dmitron.data.local

import com.dmitron.common.ResultWrapper
import com.dmitron.data.local.mappers.facade.DatabaseCityWeatherMapperFacade
import com.dmitron.domain.models.CityWeather

class CityWeatherRoomDataSource(
    private val mapper: DatabaseCityWeatherMapperFacade,
    private val dao: CityWeatherDao,
) : CityWeatherLocalDataSource {
    override suspend fun getCityWeather(cityId: Long): ResultWrapper<CityWeather?> {
        val data = dao.getById(cityId) ?: return ResultWrapper.Success(null)
        return ResultWrapper.Success(mapper.mapDatabaseCityWeather(data))
    }

    override suspend fun addCityWeather(cityWeather: CityWeather) {
        dao.insertCity(mapper.mapCityToDatabase(cityWeather.city))
        val weatherId = dao.insertWeather(mapper.mapWeatherToDatabase(cityWeather.weather, cityWeather.city.id))
        val cityId = cityWeather.city.id
        cityWeather.weather.days.forEach { day ->
            val databaseDay = mapper.mapDayToDatabase(day, weatherId, cityId)
            val dayId = dao.insertDay(databaseDay)
            day.hourlyWeather.forEach { hour ->
                val hourDatabase =
                    mapper.mapHourToDatabase(hour, dayId, cityId, day.dayOfTheWeek, weatherId)
                dao.insertHour(hourDatabase)
            }
        }
    }
}