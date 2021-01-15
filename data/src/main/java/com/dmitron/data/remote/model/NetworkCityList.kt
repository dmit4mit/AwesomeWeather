package com.dmitron.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class NetworkCityList(
    @field:Json(name = "totalCitiesFound")
    val totalCitiesFound: Int?,
    @field:Json(name = "startIndex")
    val startIndex: Int?,
    @field:Json(name = "cities")
    val cities: List<NetworkCity>?
)