package io.untungs.nutrisport

import android.app.Application
import io.untungs.nutrisport.core.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class NutriSportApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@NutriSportApp)
        }
    }
}