package com.dmitron.bottlerocketweather.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager

fun Context.layoutInflater() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

fun Context.inputMethodManager() =
    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

