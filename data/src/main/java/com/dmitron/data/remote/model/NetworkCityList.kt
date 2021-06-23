package com.dmitron.data.remote.model

import com.squareup.moshi.Json

data class NetworkCityList(
    @field:Json(name = "totalCitiesFound")
    val totalCitiesFound: Int?,
    @field:Json(name = "startIndex")
    val startIndex: Int?,
    @field:Json(name = "cities")
    val cities: List<NetworkCity>?
)