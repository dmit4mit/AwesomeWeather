package com.dmitron.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.dmitron.common.ErrorType
import com.dmitron.common.ResultWrapper
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

inline fun <T> request(
    context: Context,
    call: () -> Response<T>
): ResultWrapper<T> {
    if (!context.isNetworkAvailable()) return ResultWrapper.Failure(ErrorType.NETWORK_ERROR)
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

fun Context.connectivityManager() =
    getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = connectivityManager()
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                ?: return false
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}