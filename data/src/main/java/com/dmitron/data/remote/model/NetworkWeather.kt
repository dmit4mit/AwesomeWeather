package com.dmitron.data.remote.model

import com.squareup.moshi.Json

data class NetworkWeather(
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "days")
    val days: List<NetworkDay>? = null
) {
    data class NetworkDay(
        @field:Json(name = "dayOfTheWeek")
        val dayOfTheWeek: Int? = null,
        @field:Json(name = "low")
        val low: Int? = null,
        @field:Json(name = "high")
        val high: Int? = null,
        @field:Json(name = "weatherType")
        val weatherType: String? = null,
        @field:Json(name = "hourlyWeather")
        val hourlyWeather: List<NetworkHourlyWeather>? = null
    ) {
        data class NetworkHourlyWeather(
            @field:Json(name = "windSpeed")
            val windSpeed: Double? = null,
            @field:Json(name = "temperature")
            val temperature: Int? = null,
            @field:Json(name = "weatherType")
            val weatherType: String? = null,
            @field:Json(name = "humidity")
            val humidity: Double? = null,
            @field:Json(name = "hour")
            val hour: Int? = null,
            @field:Json(name = "rainChance")
            val rainChance: Double? = null
        )
    }
}