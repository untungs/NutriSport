package io.untungs.nutrisport

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import io.untungs.nutrisport.core.domain.model.AuthState
import io.untungs.nutrisport.core.domain.usecase.ObserveAuthStateUseCase
import io.untungs.nutrisport.core.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(
    observeAuthStateUseCase: ObserveAuthStateUseCase
) : ViewModel() {

    private val isGoogleAuthReady = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            GoogleAuthProvider.create(GoogleAuthCredentials(Constants.GOOGLE_WEB_KEY))
            isGoogleAuthReady.value = true
        }
    }

    val startDestination: StateFlow<Screen?> = combine(
        isGoogleAuthReady,
        observeAuthStateUseCase()
    ) { isReady, authState ->
        if (!isReady) return@combine null

        when (authState) {
            is AuthState.Authenticated -> Screen.HomeGraph
            is AuthState.Unauthenticated -> Screen.Auth
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
}