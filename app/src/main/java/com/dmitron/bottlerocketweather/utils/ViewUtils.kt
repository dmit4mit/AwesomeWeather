package com.dmitron.bottlerocketweather.utils

import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dmitron.bottlerocketweather.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Adds [DividerItemDecoration] for a recycle with with specified [drawable] and [orientation].
 *
 * @param orientation Divider orientation. Should be
 * [DividerItemDecoration.HORIZONTAL] or [DividerItemDecoration.VERTICAL]]
 */
fun RecyclerView.addDividerDecoration(
    @DrawableRes drawable: Int = R.drawable.divider,
    orientation: Int = DividerItemDecoration.HORIZONTAL
) =
    DividerItemDecoration(context, orientation).apply {
        ContextCompat.getDrawable(context, drawable)?.let {
            setDrawable(it)
        }
    }.also { addItemDecoration(it) }

fun EditText.onChange(onChangeCallback: (String) -> Unit) {
    doOnTextChanged { text, _, _, _ -> onChangeCallback(text.toString()) }
}

fun ViewPager2.onPageSelected(onSelectedCallback: (position: Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            onSelectedCallback(position)
        }
    })
}

fun <T> debounce(
    scope: CoroutineScope,
    destinationFun: (T) -> Unit,
    waitMs: Long = 300L,
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(waitMs)
            destinationFun(param)
        }
    }
}
