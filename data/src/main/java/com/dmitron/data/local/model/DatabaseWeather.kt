//package com.dmitron.data.local.model
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import androidx.room.Relation
//import com.dmitron.domain.models.Weather
//
//data class DatabaseWeather(
//    @PrimaryKey val id: Int,
//    val associatedCityId: Int,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "parentWeatherId"
//    )
//    val days: List<DatabaseDay>,
//) {
//    data class DatabaseDay(
//        @PrimaryKey(autoGenerate = true) val dayId: Long,
//        val parentWeatherId: Int,
//        val dayOfTheWeek: Int,
//        val low: Int,
//        val high: Int,
//        val weatherType: Weather.WeatherType,
//        @Relation(
//            parentColumn = "dayId",
//            entityColumn = "parentDayId"
//        )
//        val hourlyWeather: List<DatabaseHourlyWeather>,
//    ) {
//        @Entity(primaryKeys = ["parentDayId", "hour"])
//        data class DatabaseHourlyWeather(
//            val parentDayId: Long,
//            val hour: Int,
//            val humidity: Double,
//            val rainChance: Double,
//            val temperature: Int,
//            val weatherType: Weather.WeatherType,
//            val windSpeed: Double,
//        )
//    }
//}