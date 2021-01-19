package com.dmitron.data.local

import com.dmitron.common.ErrorType
import com.dmitron.common.ResultWrapper
import com.dmitron.data.local.mappers.facade.DatabaseCityWeatherMapperFacade
import com.dmitron.domain.models.CityWeather

class CityWeatherRoomDataSource(
    private val mapper: DatabaseCityWeatherMapperFacade,
    private val dao: CityWeatherDao,
) : CityWeatherLocalDataSource {
    override suspend fun getCityWeather(cityId: Long): ResultWrapper<CityWeather> {
        val data = dao.getById(cityId)
        return if (data == null) ResultWrapper.Failure(ErrorType.NO_DATA_FOUND)
        else ResultWrapper.Success(mapper.mapDatabaseCityWeather(data))
//        return dao.getById(cityId).mapNotNull {
//            ResultWrapper.Success(mapper.mapDatabaseCityWeather(it))
//            mapper.mapDatabaseCityWeather(it)
//        }
    }

    override suspend fun addCityWeather(cityWeather: CityWeather) {
        dao.insertCity(mapper.mapCityToDatabase(cityWeather.city))
        dao.insertWeather(mapper.mapWeatherToDatabase(cityWeather.weather, cityWeather.city.id))
        val cityId = cityWeather.city.id
        val weatherId = cityWeather.weather.id
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