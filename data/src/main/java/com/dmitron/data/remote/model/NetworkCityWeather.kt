package com.dmitron.data.remote.model


import com.squareup.moshi.Json

data class NetworkCityWeather(
    @field:Json(name = "city") val city: NetworkCity?,
    @field:Json(name = "weather") val weather: NetworkWeather?
)