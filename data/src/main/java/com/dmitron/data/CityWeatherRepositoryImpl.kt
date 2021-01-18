package com.dmitron.data

import androidx.paging.DataSource
import com.dmitron.common.ResultWrapper
import com.dmitron.data.local.CitySharedPrefDataSource
import com.dmitron.data.local.CityWeatherLocalDataSource
import com.dmitron.data.remote.CityDataSourceFactory
import com.dmitron.data.remote.CityWeatherRemoteDataSource
import com.dmitron.domain.models.City
import com.dmitron.domain.models.CityWeather
import com.dmitron.domain.repository.CityWeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CityWeatherRepositoryImpl(
//    local cache doesn't fully implemented yet
    private val localSource: CityWeatherLocalDataSource,
    private val remoteSource: CityWeatherRemoteDataSource,
    private val cityIdsLocalSource: CitySharedPrefDataSource
) : CityWeatherRepository {
    private val dispatcher = Dispatchers.IO

    override suspend fun getAllCities(): Flow<ResultWrapper<List<City>>> = flow {
        emit(ResultWrapper.Loading)
        emit(remoteSource.getAllCities())
    }.flowOn(dispatcher)

    override suspend fun getCityWeatherById(cityId: Long): Flow<ResultWrapper<CityWeather>> = flow {
        emit(ResultWrapper.Loading)
        emit(remoteSource.getCityWeather(cityId))
    }.flowOn(dispatcher)

    override suspend fun searchCities(query: String): Flow<ResultWrapper<List<City>>> = flow {
        emit(ResultWrapper.Loading)
        emit(remoteSource.searchCities(query))
    }.flowOn(dispatcher)

    override fun searchPagedCities(query: String, scope: CoroutineScope): DataSource.Factory<Int, City> =
        CityDataSourceFactory(
            remoteSource,
            scope,
            query
        )

    override fun saveCitiesIds(ids: List<Long>) {
        cityIdsLocalSource.saveCityIds(ids)
    }

    override fun getSavedCitiesIds(): List<Long> {
        return cityIdsLocalSource.getCityIds()
    }
}