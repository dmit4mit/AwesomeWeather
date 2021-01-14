package com.dmitron.bottlerocketweather.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmitron.bottlerocketweather.model.SnackbarData
import com.dmitron.bottlerocketweather.utils.Event

abstract class BaseViewModel<T> : ViewModel() {
    private val baseScreenEvent = MutableLiveData<Event<BaseScreenEvent>>()
    fun baseScreenEvent(): LiveData<Event<BaseScreenEvent>> = baseScreenEvent

    protected val _eventProvider = MutableLiveData<Event<T>>()
    val eventProvider: LiveData<Event<T>> = _eventProvider

    protected fun postEvent(event: T) {
        _eventProvider.postValue(Event(event))
    }

    protected fun setEvent(event: T) {
        _eventProvider.value = Event(event)
    }

    sealed class BaseScreenEvent {
        object NavigateBack : BaseScreenEvent()
        data class ShowSnackbar(val snackbarData: SnackbarData) : BaseScreenEvent()
    }
}