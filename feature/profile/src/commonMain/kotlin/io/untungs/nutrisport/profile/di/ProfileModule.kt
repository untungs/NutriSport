package io.untungs.nutrisport.profile.di

import io.untungs.nutrisport.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureProfileModule = module {
    viewModelOf(::ProfileViewModel)
}
