package com.dmitron.awesomeweather.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer as LifecycleObserver

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    /**
     * An [EventObserver] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
     * already been handled.
     *
     * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
     */
    class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) :
        LifecycleObserver<Event<T>> {
        override fun onChanged(event: Event<T>?) {
            event?.getContentIfNotHandled()?.let { value ->
                onEventUnhandledContent(value)
            }
        }
    }
}

fun <R> LiveData<Event<R>>.observeEvent(
    lifecycleOwner: LifecycleOwner,
    eventObserver: (R) -> Unit
) {
    observe(lifecycleOwner, Event.EventObserver(eventObserver))
}