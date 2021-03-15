package com.dmitron.awesomeweather.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisible(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        imageView.load(url)
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun setTextResource(textView: TextView, @StringRes stringRes: Int) {
        if (stringRes != 0) textView.setText(stringRes)
    }

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, @DrawableRes resource: Int) {
        imageView.setImageResource(resource)
    }
}