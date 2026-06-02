package io.untungs.nutrisport.admin.di

import io.untungs.nutrisport.admin.ManageProductViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureAdminModule = module {
    viewModelOf(::ManageProductViewModel)
}