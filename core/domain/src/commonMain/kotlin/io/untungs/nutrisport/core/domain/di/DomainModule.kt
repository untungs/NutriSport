package io.untungs.nutrisport.core.domain.di

import io.untungs.nutrisport.core.domain.usecase.CreateCustomerUseCase
import io.untungs.nutrisport.core.domain.usecase.SubmitProductUseCase
import io.untungs.nutrisport.core.domain.usecase.GetProductsUseCase
import io.untungs.nutrisport.core.domain.usecase.GetProductUseCase
import io.untungs.nutrisport.core.domain.usecase.GetCustomerUseCase
import io.untungs.nutrisport.core.domain.usecase.UpdateCustomerUseCase
import io.untungs.nutrisport.core.domain.usecase.ObserveAuthStateUseCase
import io.untungs.nutrisport.core.domain.usecase.SignOutUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::CreateCustomerUseCase)
    factoryOf(::SubmitProductUseCase)
    factoryOf(::GetProductsUseCase)
    factoryOf(::GetProductUseCase)
    factoryOf(::GetCustomerUseCase)
    factoryOf(::UpdateCustomerUseCase)
    factoryOf(::ObserveAuthStateUseCase)
    factoryOf(::SignOutUseCase)
}
