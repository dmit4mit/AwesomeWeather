package com.dmitron.data.local.model

import androidx.room.*

data class DatabaseWeatherWithDaysAndHours(
    @Embedded val weather: DatabaseWeather,
    @Relation(
        entity = DatabaseDay::class,
        parentColumn = "id",
        entityColumn = "parentWeatherId"
    )
    val days: List<DatabaseDayWithHours>,
)

data class DatabaseDayWithHours(
    @Embedded val day: DatabaseDay,
    @Relation(
        parentColumn = "dayId",
        entityColumn = "parentDayId"
    )
    val hourlyWeather: List<DatabaseHourlyWeather>,
)

@Entity(indices = [Index(value = ["associatedCityId"], unique = true)])
data class DatabaseWeather(
    @PrimaryKey val id: Int,
    val associatedCityId: Long,
)

@Entity(indices = [Index(value = ["cityId", "dayOfTheWeek"], unique = true)])
data class DatabaseDay(
    val cityId: Long,
    val parentWeatherId: Int,
    val dayOfTheWeek: Int,
    val low: Int,
    val high: Int,
    val weatherType: String,
) {
    @PrimaryKey(autoGenerate = true)
    var dayId: Long = 0
}

@Entity(
    indices = [Index(value = ["cityId", "parentDayOfWeek", "hour"], unique = true)]
)
data class DatabaseHourlyWeather(
    val cityId: Long,
    val parentDayOfWeek: Int,
    val parentDayId: Long,
    val parentWeatherId: Int,
    val hour: Int,
    val humidity: Double,
    val rainChance: Double,
    val temperature: Int,
    val weatherType: String,
    val windSpeed: Double,
) {
    @PrimaryKey(autoGenerate = true)
    var hourId: Long = 0
}