package com.dmitron.common

import androidx.annotation.StringRes

sealed class ResultWrapper<out R> {
    data class Success<out R>(val data: R) : ResultWrapper<R>()
    data class Failure(val errorType: ErrorType) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
}

enum class ErrorType {
    NO_DATA_FOUND, NETWORK_ERROR, API_ERROR, UNKNOWN_ERROR;

    @StringRes
    fun getMessage(): Int = when(this) {
        NO_DATA_FOUND -> TODO()
        NETWORK_ERROR -> TODO()
        API_ERROR -> TODO()
        UNKNOWN_ERROR -> TODO()
    }
}