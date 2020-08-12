/**
 * Copied from https://github.com/google/iosched/blob/master/shared/src/main/java/com/google/samples/apps/iosched/shared/result/LiveDataEvent.kt
 */
package com.bgpApp.common

/**
 * Copied from https://github.com/google/iosched/blob/master/shared/src/main/java/com/google/samples/apps/iosched/shared/result/LiveDataEvent.kt
 */

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
open class LiveDataEvent<out T>(private val content: T) {

    private var hasBeenHandled = false

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
}


/**
 * An [Observer] for [LiveDataEvent]s, simplifying the pattern of checking if the [LiveDataEvent]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [LiveDataEvent]'s contents has not been handled.
 */
class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<LiveDataEvent<T>> {
    override fun onChanged(liveDataEvent: LiveDataEvent<T>?) {
        liveDataEvent?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}

typealias EventEmitter = MutableLiveData<LiveDataEvent<Unit>>
fun EventEmitter.emit() {
    this.postValue(LiveDataEvent(Unit))
}

typealias DataEventEmitter<T> = MutableLiveData<LiveDataEvent<T>>
fun <T> DataEventEmitter<T>.emit(data: T) {
    this.postValue(LiveDataEvent(data))
}