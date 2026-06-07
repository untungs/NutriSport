package io.untungs.nutrisport.core.data.util

import io.github.jan.supabase.exceptions.RestException
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.untungs.nutrisport.core.domain.model.DomainException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException

fun Throwable.toDomainException(): DomainException {
    return when (this) {
        is HttpRequestTimeoutException,
        is ConnectTimeoutException,
        is TimeoutCancellationException -> DomainException.Timeout(this)

        is ResponseException -> {
            when (this.response.status.value) {
                401, 403 -> DomainException.AccessDenied(this)
                500, 503 -> DomainException.Unavailable(this)
                else -> DomainException.Unknown(this)
            }
        }

        is RestException -> {
            // Supabase RestException mapping
            DomainException.Unknown(this)
        }

        // CancellationException must be rethrown to allow Coroutines to handle cancellation correctly.
        // Swallowing it (by mapping it to a DomainException) would prevent the coroutine from
        // stopping, which can lead to leaks or inconsistent UI states.
        // Downstream usage (UseCases/ViewModels) should generally not catch or handle this exception,
        // allowing it to propagate up to the CoroutineScope.
        is CancellationException -> throw this

        else -> DomainException.Unknown(this)
    }
}
