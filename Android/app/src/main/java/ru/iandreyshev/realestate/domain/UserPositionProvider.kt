package ru.iandreyshev.realestate.domain

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object UserPositionProvider {

    fun init(applicationContext: Context) {
        // TODO: Init LocationManager
    }

    fun getUserPosition(): Flow<Position> = flowOf(Position(56.636289, 47.903888))

}
