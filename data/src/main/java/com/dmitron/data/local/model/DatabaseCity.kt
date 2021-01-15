//package com.dmitron.data.local.model
//
//import androidx.room.Embedded
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity
//data class DatabaseCity(
//    @PrimaryKey val geoNameId: Int,
//    val name: String,
//    val countryCode: String,
//    val alternateNames: String,
//    @Embedded val imageURLs: DatabaseImageURLs,
//    val latitude: Double,
//    val longitude: Double,
//    val timezone: String,
//    val modificationDate: String,
//    val population: Int,
//) {
//    data class DatabaseImageURLs(
//        val hdpiImageURL: String,
//        val mdpiImageURL: String,
//        val xhdpiImageURL: String,
//    )
//}