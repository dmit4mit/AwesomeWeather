package com.dmitron.data.remote

import androidx.paging.DataSource
import androidx.paging.PagedList
import com.dmitron.domain.models.City
import kotlinx.coroutines.CoroutineScope

class CityDataSourceFactory(
    private val cityWeatherRemoteDataSource: CityWeatherRemoteDataSource,
    private val scope: CoroutineScope,
    private val query: String
) : DataSource.Factory<Int, City>() {

    override fun create() = CityPageDataSource(cityWeatherRemoteDataSource, scope, query)

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun pagedListConfig(firstPageSize: Int = DEFAULT_PAGE_SIZE) = PagedList.Config.Builder()
            .setInitialLoadSizeHint(firstPageSize)
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}