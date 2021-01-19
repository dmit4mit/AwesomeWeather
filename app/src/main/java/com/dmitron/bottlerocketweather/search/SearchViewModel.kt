package com.dmitron.bottlerocketweather.search

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.search.adapter.SearchItem
import com.dmitron.bottlerocketweather.search.adapter.toSearchItem
import com.dmitron.common.ErrorType
import com.dmitron.data.remote.CityDataSourceFactory
import com.dmitron.domain.usecases.SearchCitiesUseCase
import com.dmitron.domain.usecases.SearchPagedCitiesUseCase
import kotlinx.coroutines.flow.collect

class SearchViewModel(
    private val searchPagedCitiesUseCase: SearchPagedCitiesUseCase
) : BaseViewModel<SearchScreenEvent>() {

    private val query = MutableLiveData<String>()

    private val _displayError = MutableLiveData<Int>()
    val displayError: LiveData<Int> = _displayError

    val foundCities = query.switchMap { query ->
            buildPaginatedSearchLiveData(query)
//            searchCitiesUseCase(SearchCitiesUseCase.Params(query)).collect { result ->
//                handleResult(result) { cities ->
//                    _displayError.value = 0
//                    if (cities.isEmpty() && query != "") handleFailure(ErrorType.NO_DATA_FOUND)
//                    emit(cities.map { it.toSearchItem() })
//                }
//            }
    }

    private fun buildPaginatedSearchLiveData(query: String): LiveData<PagedList<SearchItem>> {
        val factory = searchPagedCitiesUseCase(
            SearchPagedCitiesUseCase.Params(viewModelScope, query)
        ).map { it.toSearchItem() }

        return LivePagedListBuilder(
            factory,
            CityDataSourceFactory.pagedListConfig()
        ).build()
    }

    override fun handleFailure(failure: ErrorType) {
        _displayError.value = failure.getMessage()
    }

    fun onSearchTextChanged(text: String) {
        query.value = text
    }

    fun onSearchItemClicked(searchItem: SearchItem) {
        setEvent(SearchScreenEvent.SetSearchResult(searchItem.cityId))
        setEvent(SearchScreenEvent.HideKeyboard)
        navigateBack()
    }

    fun onCloseClicked() {
        setEvent(SearchScreenEvent.HideKeyboard)
        navigateBack()
    }
}

sealed class SearchScreenEvent {
    data class SetSearchResult(val cityId: Long) : SearchScreenEvent()
    object ShowKeyboard : SearchScreenEvent()
    object HideKeyboard : SearchScreenEvent()
}