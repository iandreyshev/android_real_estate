package ru.iandreyshev.realestate

import android.app.Application
import ru.iandreyshev.realestate.domain.UserPositionProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        UserPositionProvider.init(this)
    }

}