package io.untungs.nutrisport.core.data.di

import io.untungs.nutrisport.core.data.repository.CustomerRepositoryImpl
import io.untungs.nutrisport.core.data.repository.SessionRepositoryImpl
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import io.untungs.nutrisport.core.domain.repository.SessionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::CustomerRepositoryImpl) bind CustomerRepository::class
    singleOf(::SessionRepositoryImpl) bind SessionRepository::class
}
