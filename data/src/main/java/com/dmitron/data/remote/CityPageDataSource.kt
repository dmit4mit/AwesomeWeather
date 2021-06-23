package com.dmitron.data.remote

import androidx.paging.PageKeyedDataSource
import com.dmitron.domain.models.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CityPageDataSource(
    private val cityWeatherRemoteDataSource: CityWeatherRemoteDataSource,
    private val scope: CoroutineScope,
    private val query: String
) : PageKeyedDataSource<Int, City>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, City>
    ) {
        loadData(1, params.requestedLoadSize) {
            val key = if (it.size < params.requestedLoadSize) null else 2
            callback.onResult(it, null, key)
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, City>
    ) {
        loadData(params.key, params.requestedLoadSize) {
            val key = if (it.size < params.requestedLoadSize) null else params.key + 1
            callback.onResult(it, key)
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, City>
    ) {
        loadData(params.key, params.requestedLoadSize) {
            val key = if (it.size < params.requestedLoadSize || params.key <= 1) null else params.key - 1
            callback.onResult(it, key)
        }
    }

    private fun loadData(
        pageNumber: Int,
        pageCount: Int,
        callback: (List<City>) -> Unit
    ) {
        scope.launch {
            val cities = cityWeatherRemoteDataSource.searchCities(query, pageCount, pageNumber)
                .successOrNull() ?: listOf()
            callback(cities)
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}