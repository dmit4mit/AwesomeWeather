package com.dmitron.awesomeweather.utils

import androidx.databinding.ObservableList

fun <T> ObservableList<T>.registerSimpleOnListChangedListener(onChange: (ObservableList<T>) -> Unit):
        ObservableList.OnListChangedCallback<ObservableList<T>> {
    val listener = object : ObservableList.OnListChangedCallback<ObservableList<T>>() {
        override fun onChanged(sender: ObservableList<T>) {
            onChange(sender)
        }

        override fun onItemRangeChanged(
            sender: ObservableList<T>,
            positionStart: Int,
            itemCount: Int
        ) {
            onChange(sender)
        }

        override fun onItemRangeInserted(
            sender: ObservableList<T>,
            positionStart: Int,
            itemCount: Int
        ) {
            onChange(sender)
        }

        override fun onItemRangeMoved(
            sender: ObservableList<T>,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {
            onChange(sender)
        }

        override fun onItemRangeRemoved(
            sender: ObservableList<T>,
            positionStart: Int,
            itemCount: Int
        ) {
            onChange(sender)
        }

    }
    addOnListChangedCallback(listener)

    return listener
}