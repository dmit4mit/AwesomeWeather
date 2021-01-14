package com.dmitron.data.remote.model

import com.squareup.moshi.Json

data class NetworkWeather(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "days")
    val days: List<NetworkDay>? = null
) {
    data class NetworkDay(
        @Json(name = "dayOfTheWeek")
        val dayOfTheWeek: Int? = null,
        @Json(name = "low")
        val low: Int? = null,
        @Json(name = "high")
        val high: Int? = null,
        @Json(name = "weatherType")
        val weatherType: String? = null,
        @Json(name = "hourlyWeather")
        val hourlyWeather: List<NetworkHourlyWeather>? = null
    ) {
        data class NetworkHourlyWeather(
            @Json(name = "windSpeed")
            val windSpeed: Double? = null,
            @Json(name = "temperature")
            val temperature: Int? = null,
            @Json(name = "weatherType")
            val weatherType: String? = null,
            @Json(name = "humidity")
            val humidity: Double? = null,
            @Json(name = "hour")
            val hour: Int? = null,
            @Json(name = "rainChance")
            val rainChance: Double? = null
        )
    }
}