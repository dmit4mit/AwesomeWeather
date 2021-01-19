package com.dmitron.bottlerocketweather.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
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

@Suppress("DEPRECATION")
fun Window.enableFullscreen() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        setDecorFitsSystemWindows(false)

//        insetsController?.run {
//            hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
//            systemBarsBehavior =
//                WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        }
    } else {
        decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }
}

@Suppress("DEPRECATION")
fun Window.showSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        setDecorFitsSystemWindows(true)
        insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
    } else {
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}