package io.untungs.nutrisport.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseUser
import io.untungs.nutrisport.core.domain.usecase.CreateCustomerUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface AuthEvent {
    data class Success(val displayName: String) : AuthEvent
    data class Error(val message: String) : AuthEvent
}

class AuthViewModel(
    private val createCustomer: CreateCustomerUseCase
) : ViewModel() {

    private val _events = Channel<AuthEvent>()
    val events: Flow<AuthEvent> = _events.receiveAsFlow()

    fun onSignInResult(result: Result<FirebaseUser?>) {
        result.onSuccess { user ->
            if (user != null) {
                handleSignInSuccess(user)
            }
        }.onFailure { exception ->
            viewModelScope.launch {
                val errorMessage = if (exception.message == "Idtoken is null") {
                    "Sign in canceled or no internet connection"
                } else {
                    exception.message ?: "Something went wrong"
                }
                _events.send(AuthEvent.Error(errorMessage))
            }
        }
    }

    private fun handleSignInSuccess(user: FirebaseUser) {
        viewModelScope.launch {
            createCustomer(
                id = user.uid,
                displayName = user.displayName ?: "",
                email = user.email ?: ""
            ).onSuccess {
                _events.send(AuthEvent.Success(user.displayName ?: ""))
            }.onFailure { exception ->
                _events.send(AuthEvent.Error(exception.message ?: "Failed to create customer"))
            }
        }
    }
}