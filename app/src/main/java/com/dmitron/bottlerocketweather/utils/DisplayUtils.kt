package com.dmitron.bottlerocketweather.utils

import android.content.Context
import org.koin.core.KoinComponent
import org.koin.core.inject

object DisplayUtils : KoinComponent {
    private val context: Context by inject()

    fun getScreenDensity(): Density {
        val density = context.resources.displayMetrics.density
        return when {
            density >= 3.0 -> Density.LARGER
            density >= 2.0 -> Density.XHDPI
            density >= 1.5 -> Density.HDPI
            density >= 1.0 -> Density.MDPI
            else -> Density.SMALLER
        }
    }

    enum class Density {
        MDPI, HDPI, XHDPI, SMALLER, LARGER
    }
}