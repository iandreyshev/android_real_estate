package ru.iandreyshev.realestate

import android.app.Application
import ru.iandreyshev.realestate.data.UserPositionProvider
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        UserPositionProvider.init(this)
        Timber.plant(Timber.DebugTree())
    }

}