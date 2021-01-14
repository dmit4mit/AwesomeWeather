package com.dmitron.domain.models

data class City(
    val name: String,
    val countryCode: String,
    val imageURLs: ImageURLs,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val modificationDate: String,
    val population: Int,
) {
    data class ImageURLs(
        val hdpiImageURL: String,
        val mdpiImageURL: String,
        val xhdpiImageURL: String,
    )
}