package ru.iandreyshev.realestate.extension

data class Event<T>(
    val value: T
) {

    private var mIsConsumed: Boolean = false

    fun consume(consumer: (T) -> Unit) {
        if (!mIsConsumed) {
            consumer(value)
            mIsConsumed = true
        }
    }

}