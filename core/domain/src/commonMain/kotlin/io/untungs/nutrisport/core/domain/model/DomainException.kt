package io.untungs.nutrisport.core.domain.model

sealed class DomainException(
    override val message: String,
    override val cause: Throwable? = null
) : Exception(message, cause) {
    class Network(cause: Throwable) : DomainException("No internet connection", cause)
    class Timeout(cause: Throwable) : DomainException("Request timed out", cause)
    class AccessDenied(cause: Throwable? = null) : DomainException("You don't have permission to do this", cause)
    class Unavailable(cause: Throwable? = null) : DomainException("Service is temporarily unavailable", cause)
    class Unknown(cause: Throwable) : DomainException("Something went wrong", cause)
}
