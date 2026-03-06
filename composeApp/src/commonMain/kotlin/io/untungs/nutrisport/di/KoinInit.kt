package io.untungs.nutrisport.di

import io.untungs.nutrisport.auth.di.featureAuthModule
import io.untungs.nutrisport.core.data.di.dataModule
import io.untungs.nutrisport.core.domain.di.domainModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initializeKoin(
    config: KoinApplication.() -> Unit = {}
) {
    startKoin {
        config()
        modules(
            domainModule,
            dataModule,
            appModule,
            featureAuthModule,
        )
    }
}