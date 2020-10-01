package ru.iandreyshev.realestate.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<Event<T>>.consume(lifecycleOwner: LifecycleOwner, consumer: (T) -> Unit) =
    observe(lifecycleOwner) { event ->
        event.consume { consumer(it) }
    }
