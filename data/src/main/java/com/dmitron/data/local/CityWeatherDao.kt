//package com.dmitron.data.local
//
//import androidx.room.*
//import com.dmitron.data.local.model.DatabaseCityWeather
//
//@Dao
//interface CityWeatherDao {
//    @Insert
//    fun insertAll(vararg items: DatabaseCityWeather)
//
//    @Transaction
//    @Query("SELECT * FROM DatabaseCity")
//    fun getAll(): List<DatabaseCityWeather>
//
//    @Transaction
//    @Query("SELECT * FROM DatabaseCity WHERE geoNameId = :cityId")
//    fun getById(cityId: Int)
//
//    @Delete
//    fun delete(cityWeather: DatabaseCityWeather)
//}