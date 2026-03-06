package io.untungs.nutrisport.core.domain.di

import io.untungs.nutrisport.core.domain.usecase.CreateCustomerUseCase
import io.untungs.nutrisport.core.domain.usecase.ObserveAuthStateUseCase
import io.untungs.nutrisport.core.domain.usecase.SignOutUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::CreateCustomerUseCase)
    factoryOf(::ObserveAuthStateUseCase)
    factoryOf(::SignOutUseCase)
}
