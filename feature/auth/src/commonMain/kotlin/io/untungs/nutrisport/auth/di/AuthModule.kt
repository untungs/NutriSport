package io.untungs.nutrisport.auth.di

import io.untungs.nutrisport.auth.AuthViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureAuthModule = module {
    viewModelOf(::AuthViewModel)
}
