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
    @Insert
    fun insertCity(item: DatabaseCity)

    @Transaction
    @Insert
    fun insertWeather(items: DatabaseWeather)

    @Transaction
    @Insert
    fun insertDay(item: DatabaseDay): Long

    @Insert
    fun insertHour(item: DatabaseHourlyWeather)
}