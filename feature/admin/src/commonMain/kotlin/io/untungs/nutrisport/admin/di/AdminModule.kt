package io.untungs.nutrisport.admin.di

import io.untungs.nutrisport.admin.AdminPanelViewModel
import io.untungs.nutrisport.admin.ManageProductViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureAdminModule = module {
    viewModelOf(::AdminPanelViewModel)
    viewModelOf(::ManageProductViewModel)
}