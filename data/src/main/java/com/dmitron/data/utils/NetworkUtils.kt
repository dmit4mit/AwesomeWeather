package com.dmitron.data.utils

import com.dmitron.common.ErrorType
import com.dmitron.common.ResultWrapper
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

inline fun <T> request(
    call: () -> Response<T>
): ResultWrapper<T> {
    return try {
        val response = call.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            ResultWrapper.Success(body)
        } else {
            Timber.e(HttpException(response))
            ResultWrapper.Failure(ErrorType.API_ERROR, HttpException(response))
        }
    } catch (throwable: Throwable) {
        Timber.e(throwable)
        ResultWrapper.Failure(ErrorType.API_ERROR)
    }
}