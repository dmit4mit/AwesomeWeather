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
    val associatedCityId: Long,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

@Entity(indices = [Index(value = ["cityId", "dayOfTheWeek"], unique = true)])
data class DatabaseDay(
    val cityId: Long = 0,
    val parentWeatherId: Long = 0,
    val dayOfTheWeek: Int = 0,
    val low: Int = 0,
    val high: Int = 0,
    val weatherType: String = "",
) {
    @PrimaryKey(autoGenerate = true)
    var dayId: Long = 0
}

@Entity(
    indices = [Index(value = ["cityId", "parentDayOfWeek", "hour"], unique = true)]
)
data class DatabaseHourlyWeather(
    val cityId: Long = 0,
    val parentDayOfWeek: Int = 0,
    val parentDayId: Long = 0,
    val parentWeatherId: Long = 0,
    val hour: Int = 0,
    val humidity: Double = 0.0,
    val rainChance: Double = 0.0,
    val temperature: Int = 0,
    val weatherType: String = "",
    val windSpeed: Double = 0.0,
) {
    @PrimaryKey(autoGenerate = true)
    var hourId: Long = 0
}