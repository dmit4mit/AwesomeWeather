package com.dmitron.domain.models

data class Weather(
    val days: List<Day>,
    val id: Int,
) {
    data class Day(
        val dayOfTheWeek: Int,
        val low: Int,
        val high: Int,
        val weatherType: WeatherType,
        val hourlyWeather: List<HourlyWeather>,
    ) {
        data class HourlyWeather(
            val hour: Int,
            val humidity: Double,
            val rainChance: Double,
            val temperature: Int,
            val weatherType: WeatherType,
            val windSpeed: Double,
        )
    }

    enum class WeatherType {
        SUNNY, CLOUDY, LIGHT_RAIN, PARTLY_CLOUDY, HEAVY_RAIN, SNOW_SLEET
    }
}