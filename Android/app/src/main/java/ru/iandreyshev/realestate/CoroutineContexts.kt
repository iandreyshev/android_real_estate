package ru.iandreyshev.realestate

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoroutineContexts(val io: CoroutineContext) {

    companion object {
        fun default() = CoroutineContexts(io = Dispatchers.IO)
    }

}
