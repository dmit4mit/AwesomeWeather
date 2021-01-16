package com.dmitron.bottlerocketweather.utils


sealed class SnackbarData {
    data class StringRes(@androidx.annotation.StringRes val stringRes: Int) : SnackbarData()
    data class Text(val text: String) : SnackbarData()
}