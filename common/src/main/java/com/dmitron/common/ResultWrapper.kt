package com.dmitron.common

import androidx.annotation.StringRes
import kotlin.Exception

sealed class ResultWrapper<out R> {
    data class Success<out R>(val data: R) : ResultWrapper<R>()
    data class Failure(val errorType: ErrorType, val exception: Exception? = null) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()

    fun successOrNull() = if (this is Success) data else null

    inline fun <T> map(fn: (R) -> (T)): ResultWrapper<T> =
        when (this) {
            is Success -> Success(fn(data))
            is Failure -> this
            Loading -> Loading
        }

    inline fun ifSuccess(fn: (R) -> Unit) {
        if (this is Success) fn(data)
    }

    inline fun <T> flatMap(fn: (R) -> ResultWrapper<T>): ResultWrapper<T> =
        when (this) {
            is Success -> fn(data)
            is Failure -> this
            Loading -> Loading
        }
}

enum class ErrorType {
    NO_DATA_FOUND, NETWORK_ERROR, API_ERROR, UNKNOWN_ERROR;

    @StringRes
    fun getMessage(): Int = when(this) {
        NO_DATA_FOUND -> R.string.error_message_no_data
        NETWORK_ERROR -> R.string.error_message_network
        API_ERROR -> R.string.error_message_api
        UNKNOWN_ERROR -> R.string.error_message_unknown
    }
}