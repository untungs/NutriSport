package io.untungs.nutrisport.core.data.di

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.Storage
import io.ktor.client.plugins.HttpTimeout
import io.untungs.nutrisport.core.data.repository.CustomerRepositoryImpl
import io.untungs.nutrisport.core.data.repository.ImageRepositoryImpl
import io.untungs.nutrisport.core.data.repository.ProductAdminRepositoryImpl
import io.untungs.nutrisport.core.data.repository.SessionRepositoryImpl
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import io.untungs.nutrisport.core.domain.repository.ImageRepository
import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository
import io.untungs.nutrisport.core.domain.repository.SessionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds

@OptIn(SupabaseInternal::class)
val dataModule = module {
    singleOf(::CustomerRepositoryImpl) bind CustomerRepository::class
    singleOf(::ProductAdminRepositoryImpl) bind ProductAdminRepository::class
    singleOf(::SessionRepositoryImpl) bind SessionRepository::class
    singleOf(::ImageRepositoryImpl) bind ImageRepository::class

    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = "",
            supabaseKey = ""
        ) {
            install(Storage)
            requestTimeout = 60.seconds
            httpConfig {
                install(HttpTimeout) {
                    requestTimeoutMillis = 60000
                    connectTimeoutMillis = 30000
                }
            }
        }
    }
}
