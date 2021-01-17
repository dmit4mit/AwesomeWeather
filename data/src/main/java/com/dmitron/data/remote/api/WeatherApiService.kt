package com.dmitron.data.remote.api

import com.dmitron.data.remote.model.NetworkCityList
import com.dmitron.data.remote.model.NetworkCityWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {
    @GET("cities/{id}")
    suspend fun getCityWeather(
        @Path("id") cityId: Long
    ): Response<NetworkCityWeather>

    @GET("cities")
    suspend fun getCityList(
        @Query("search") search: String,
        @Query("pageCount") pageCount: Int,
        @Query("pageNumber") pageNumber: Int,
    ): Response<NetworkCityList>

    @GET("cities")
    suspend fun getCityList(
        @Query("search") search: String,
    ): Response<NetworkCityList>

    @GET("cities")
    suspend fun getCityList(): Response<NetworkCityList>
}