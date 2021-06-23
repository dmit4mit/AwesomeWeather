package com.dmitron.data.remote.model

import com.squareup.moshi.Json

data class NetworkCity(
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "country code")
    val countryCode: String? = null,
    @field:Json(name = "alternatenames")
    val alternateNames: String? = null,
    @field:Json(name = "elevation")
    val elevation: Int? = null,
    @field:Json(name = "imageURLs")
    val imageURLs: NetworkImageURLs? = null,
    @field:Json(name = "geonameid")
    val geoNameId: Long? = null,
    @field:Json(name = "modification date")
    val modificationDate: String? = null,
    @field:Json(name = "feature code")
    val featureCode: String? = null,
    @field:Json(name = "dem")
    val dem: Int? = null,
    @field:Json(name = "feature class")
    val featureClass: String? = null,
    @field:Json(name = "longitude")
    val longitude: Double? = null,
    @field:Json(name = "asciiname")
    val asciiName: String? = null,
    @field:Json(name = "latitude")
    val latitude: Double? = null,
    @field:Json(name = "timezone")
    val timezone: String? = null,
    @field:Json(name = "population")
    val population: Int? = null,
) {
    data class NetworkImageURLs(
        @field:Json(name = "androidImageURLs")
        val androidImageURLs: AndroidImageURLs? = null,
    ) {
        data class AndroidImageURLs(
            @field:Json(name = "xhdpiImageURL")
            val xhdpiImageURL: String? = null,
            @field:Json(name = "hdpiImageURL")
            val hdpiImageURL: String? = null,
            @field:Json(name = "mdpiImageURL")
            val mdpiImageURL: String? = null,
        )
    }
}