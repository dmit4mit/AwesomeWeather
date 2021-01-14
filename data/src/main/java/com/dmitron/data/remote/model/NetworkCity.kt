package com.dmitron.data.remote.model

import com.squareup.moshi.Json

data class NetworkCity(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "country code")
    val countryCode: String? = null,
    @Json(name = "alternatenames")
    val alternateNames: String? = null,
    @Json(name = "elevation")
    val elevation: Int? = null,
    @Json(name = "imageURLs")
    val imageURLs: NetworkImageURLs? = null,
    @Json(name = "geonameid")
    val geoNameId: Int? = null,
    @Json(name = "modification date")
    val modificationDate: String? = null,
    @Json(name = "feature code")
    val featureCode: String? = null,
    @Json(name = "dem")
    val dem: Int? = null,
    @Json(name = "feature class")
    val featureClass: String? = null,
    @Json(name = "longitude")
    val longitude: Double? = null,
    @Json(name = "asciiname")
    val asciiName: String? = null,
    @Json(name = "latitude")
    val latitude: Double? = null,
    @Json(name = "timezone")
    val timezone: String? = null,
    @Json(name = "population")
    val population: Int? = null,
) {
    data class NetworkImageURLs(
        @Json(name = "androidImageURLs")
        val androidImageURLs: AndroidImageURLs? = null,
    ) {
        data class AndroidImageURLs(
            @Json(name = "xhdpiImageURL")
            val xhdpiImageURL: String? = null,
            @Json(name = "hdpiImageURL")
            val hdpiImageURL: String? = null,
            @Json(name = "mdpiImageURL")
            val mdpiImageURL: String? = null,
        )
    }
}