package io.untungs.nutrisport.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initializeKoin(
    config: KoinApplication.() -> Unit = {}
) {
    startKoin {
        config()
    }
}