package com.dmitron.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class DatabaseCityWeather(
    @Embedded val city: DatabaseCity,
    @Relation(
        entity = DatabaseWeather::class,
        parentColumn = "geoNameId",
        entityColumn = "associatedCityId"
    )
    val weather: DatabaseWeatherWithDaysAndHours?,
)