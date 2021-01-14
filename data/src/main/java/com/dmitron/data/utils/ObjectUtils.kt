package com.dmitron.data.utils

fun <T> T?.orDefault(default: T) = this ?: default

fun Int?.orDefault(default: Int = 0) = this ?: default

fun Double?.orDefault(default: Double = 0.0) = this ?: default