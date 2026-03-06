package io.untungs.nutrisport.core.domain.model

sealed interface AuthState {
    data class Authenticated(val uid: String) : AuthState
    data object Unauthenticated : AuthState
}
