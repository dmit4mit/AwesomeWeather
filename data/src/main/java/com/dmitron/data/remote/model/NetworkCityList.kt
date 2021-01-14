package com.dmitron.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkCityList(
    @Json(name = "totalCitiesFound")
    val totalCitiesFound: Int?,
    @Json(name = "startIndex")
    val startIndex: Int?,
    @Json(name = "cities")
    val cities: List<NetworkCity>?
)