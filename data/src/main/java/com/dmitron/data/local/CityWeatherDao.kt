package com.dmitron.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.dmitron.data.local.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityWeatherDao {

    @Transaction
    @Query("SELECT * FROM DatabaseCity")
    fun getAll(): Flow<List<DatabaseCityWeather>>

    @Transaction
    @Query("SELECT * FROM DatabaseCity WHERE geoNameId = :cityId")
    fun getById(cityId: Long): Flow<DatabaseCityWeather>

    @Transaction
    fun insertWeather(databaseWeather: DatabaseWeather, days: List<DatabaseDay>, hours: List<DatabaseHourlyWeather>) {
//        cityWeather.
    }

    @Transaction
    fun insertCityWeather(cityWeather: DatabaseWeather, days: List<DatabaseDay>, hours: List<DatabaseHourlyWeather>) {
//        cityWeather.
    }

    @Insert
    fun insertCity(item: DatabaseCity)

    @Insert
    fun insertWeather(item: DatabaseWeather)

    @Insert
    fun insertDays(item: DatabaseDay)

    @Insert
    fun insertHours(item: DatabaseHourlyWeather)
}