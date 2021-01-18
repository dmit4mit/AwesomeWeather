package com.dmitron.bottlerocketweather.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.dmitron.bottlerocketweather.R
import com.dmitron.bottlerocketweather.base.BaseViewModel
import com.dmitron.bottlerocketweather.search.adapter.SearchItem
import com.dmitron.bottlerocketweather.search.adapter.toSearchItem
import com.dmitron.common.ErrorType
import com.dmitron.domain.usecases.SearchCitiesUseCase
import kotlinx.coroutines.flow.collect

class SearchViewModel(
    private val searchCitiesUseCase: SearchCitiesUseCase
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