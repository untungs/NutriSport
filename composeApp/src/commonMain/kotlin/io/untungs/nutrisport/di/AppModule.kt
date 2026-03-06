package io.untungs.nutrisport.di

import io.untungs.nutrisport.AppViewModel
import io.untungs.nutrisport.core.ui.AppCoroutineScope
import io.untungs.nutrisport.core.ui.AppMessageManager
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::AppCoroutineScope)
    singleOf(::AppMessageManager)
    viewModelOf(::AppViewModel)
}
