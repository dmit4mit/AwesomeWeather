package com.dmitron.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmitron.data.local.model.*

@Database(
    entities = [DatabaseCity::class, DatabaseWeather::class, DatabaseHourlyWeather::class, DatabaseDay::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityWeatherDao(): CityWeatherDao
}