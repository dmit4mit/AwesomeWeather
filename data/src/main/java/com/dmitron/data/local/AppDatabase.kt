package com.dmitron.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dmitron.data.local.model.DatabaseCity
import com.dmitron.data.local.model.DatabaseWeather
import com.dmitron.data.local.model.DatabaseWeather.DatabaseDay.DatabaseHourlyWeather

@Database(
    entities = [
        DatabaseCity::class,
        DatabaseWeather::class,
        DatabaseWeather.DatabaseDay::class,
        DatabaseHourlyWeather::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityWeatherDao(): CityWeatherDao
}