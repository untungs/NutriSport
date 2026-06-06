package io.untungs.nutrisport.di

import io.untungs.nutrisport.admin.di.featureAdminModule
import io.untungs.nutrisport.auth.di.featureAuthModule
import io.untungs.nutrisport.core.data.di.dataModule
import io.untungs.nutrisport.core.domain.di.domainModule
import io.untungs.nutrisport.home.di.featureHomeModule
import io.untungs.nutrisport.profile.di.featureProfileModule
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
            featureHomeModule,
            featureProfileModule,
            featureAdminModule,
        )
    }
}