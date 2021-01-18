package com.dmitron.bottlerocketweather.search

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.search.adapter.SearchItem
import com.dmitron.bottlerocketweather.search.adapter.toSearchItem
import com.dmitron.common.ErrorType
import com.dmitron.data.remote.CityDataSourceFactory
import com.dmitron.domain.models.City
import com.dmitron.domain.usecases.SearchCitiesUseCase
import com.dmitron.domain.usecases.SearchPagedCitiesUseCase
import kotlinx.coroutines.flow.collect

class SearchViewModel(
    private val searchCitiesUseCase: SearchCitiesUseCase,
//    pagination is turned off, because API returns 502 errors for requests with pageNumbers
//    private val searchPagedCitiesUseCase: SearchPagedCitiesUseCase
) : BaseViewModel<SearchScreenEvent>() {

    private val query = MutableLiveData<String>()

    private val _displayError = MutableLiveData<Int>()
    val displayError: LiveData<Int> = _displayError

    val foundCities = query.switchMap { query ->
        liveData {
            searchCitiesUseCase(SearchCitiesUseCase.Params(query)).collect { result ->
                handleResult(result) { cities ->
                    _displayError.value = 0
                    if (cities.isEmpty() && query != "") handleFailure(ErrorType.NO_DATA_FOUND)
                    emit(cities.map { it.toSearchItem() })
                }
            }
        }
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