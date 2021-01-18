package com.dmitron.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

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

@Entity
data class DatabaseWeather(
    @PrimaryKey val id: Int,
    val associatedCityId: Int,
)

@Entity
data class DatabaseDay(
    val parentWeatherId: Int,
    val dayOfTheWeek: Int,
    val low: Int,
    val high: Int,
    val weatherType: String,
) {
    @PrimaryKey(autoGenerate = true) var dayId: Long = 0
}

@Entity(primaryKeys = ["parentDayId", "hour"])
data class DatabaseHourlyWeather(
    val parentDayId: Long,
    val hour: Int,
    val humidity: Double,
    val rainChance: Double,
    val temperature: Int,
    val weatherType: String,
    val windSpeed: Double,
)