package io.untungs.nutrisport.home.di

import io.untungs.nutrisport.home.HomeGraphViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureHomeModule = module {
    viewModelOf(::HomeGraphViewModel)
}
