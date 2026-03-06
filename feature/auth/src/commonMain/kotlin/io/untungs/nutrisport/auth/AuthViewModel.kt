package io.untungs.nutrisport.auth

import androidx.lifecycle.ViewModel
import dev.gitlive.firebase.auth.FirebaseUser
import io.untungs.nutrisport.core.domain.usecase.CreateCustomerUseCase
import io.untungs.nutrisport.core.ui.AppCoroutineScope
import io.untungs.nutrisport.core.ui.AppMessageManager
import kotlinx.coroutines.launch

class AuthViewModel(
    private val createCustomer: CreateCustomerUseCase,
    private val appScope: AppCoroutineScope,
    private val appMessageManager: AppMessageManager
) : ViewModel() {

    fun onSignInResult(result: Result<FirebaseUser?>) {
        result.onSuccess { user ->
            if (user != null) {
                handleSignInSuccess(user)
            }
        }.onFailure { exception ->
            val errorMessage = if (exception.message == "Idtoken is null") {
                "Sign in canceled or no internet connection"
            } else {
                exception.message ?: "Something went wrong"
            }
            appMessageManager.showError(errorMessage)
        }
    }

    private fun handleSignInSuccess(user: FirebaseUser) {
        appScope.launch {
            createCustomer(
                id = user.uid,
                displayName = user.displayName ?: "",
                email = user.email ?: ""
            ).onSuccess {
                appMessageManager.showSuccess("Signed in successfully as ${user.displayName}")
            }.onFailure { exception ->
                appMessageManager.showError(exception.message ?: "Failed to create customer")
            }
        }
    }
}