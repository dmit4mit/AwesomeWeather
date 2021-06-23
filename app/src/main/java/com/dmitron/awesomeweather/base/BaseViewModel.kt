package com.dmitron.awesomeweather.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmitron.awesomeweather.utils.SnackbarData
import com.dmitron.awesomeweather.utils.Event
import com.dmitron.common.ErrorType
import com.dmitron.common.ResultWrapper

abstract class BaseViewModel<T> : ViewModel() {
    private val baseScreenEvent = MutableLiveData<Event<BaseScreenEvent>>()
    fun baseScreenEvent(): LiveData<Event<BaseScreenEvent>> = baseScreenEvent

    protected val _eventProvider = MutableLiveData<Event<T>>()
    val eventProvider: LiveData<Event<T>> = _eventProvider

    protected val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    protected fun postEvent(event: T) {
        _eventProvider.postValue(Event(event))
    }

    protected fun setEvent(event: T) {
        _eventProvider.value = Event(event)
    }

    protected inline fun <R> handleResult(result: ResultWrapper<R>, successFn: (R) -> Unit) {
        when (result) {
            is ResultWrapper.Success -> successFn(result.data).also { setLoading(false) }
            is ResultWrapper.Failure -> handleFailure(result.errorType).also { setLoading(false) }
            ResultWrapper.Loading -> setLoading(true)
        }
    }

    protected open fun handleFailure(failure: ErrorType) {
        showSnackbar(failure.getMessage())
    }

    protected fun setLoading(value: Boolean) {
        _isLoading.value = value
    }

    protected fun navigateBack() {
        baseScreenEvent.value = Event(BaseScreenEvent.NavigateBack)
    }

    protected fun showSnackbar(@StringRes stringRes: Int) {
        baseScreenEvent.value = Event(BaseScreenEvent.ShowSnackbar(SnackbarData.StringRes(stringRes)))
    }

    sealed class BaseScreenEvent {
        object NavigateBack : BaseScreenEvent()
        data class ShowSnackbar(val snackbarData: SnackbarData) : BaseScreenEvent()
    }
}