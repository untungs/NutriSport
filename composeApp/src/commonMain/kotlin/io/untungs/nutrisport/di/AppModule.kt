package io.untungs.nutrisport.di

import io.untungs.nutrisport.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::AppViewModel)
}
