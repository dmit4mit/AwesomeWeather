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

    enum class WeatherType(val title: String) {
        SUNNY("sunny"),
        CLOUDY("cloudy"),
        LIGHT_RAIN("lightRain"),
        PARTLY_CLOUDY("partlyCloudy"),
        HEAVY_RAIN("heavyRain"),
        SNOW_SLEET("snowSleet");

        companion object {
            fun fromName(name: String) = values().find { it.title == name } ?: SUNNY
        }
    }
}