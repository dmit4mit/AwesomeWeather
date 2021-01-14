package com.dmitron.data.remote.model


import com.squareup.moshi.Json

data class NetworkCityWeather(
    @Json(name = "city") val city: NetworkCity?,
    @Json(name = "weather") val weather: NetworkWeather?
)