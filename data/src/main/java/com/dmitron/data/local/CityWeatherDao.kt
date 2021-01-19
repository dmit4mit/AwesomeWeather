package com.dmitron.data.local

import androidx.room.*
import com.dmitron.data.local.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CityWeatherDao {

    @Transaction
    @Query("SELECT * FROM DatabaseCity")
    fun getAll(): Flow<List<DatabaseCityWeather>>

    @Transaction
    @Query("SELECT * FROM DatabaseCity WHERE geoNameId = :cityId")
    fun getById(cityId: Long): DatabaseCityWeather?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(item: DatabaseCity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(items: DatabaseWeather)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDay(item: DatabaseDay): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHour(item: DatabaseHourlyWeather)
}