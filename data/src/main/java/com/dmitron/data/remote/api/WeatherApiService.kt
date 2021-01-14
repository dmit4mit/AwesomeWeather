package com.dmitron.data.remote.api

import com.dmitron.data.remote.model.NetworkCityList
import com.dmitron.data.remote.model.NetworkCityWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("cities")
    suspend fun getCityWeather(
        @Query("cityID") cityId: Int
    ): Response<NetworkCityWeather>

    @GET("cities")
    suspend fun getCityList(
        @Query("search") search: String,
        @Query("pageCount") pageCount: Int,
        @Query("pageNumber") pageNumber: Int,
    ): Response<NetworkCityList>
}