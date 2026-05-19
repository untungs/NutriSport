package io.untungs.nutrisport.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.usecase.SignOutUseCase
import kotlinx.coroutines.launch

class HomeGraphViewModel(
    private val signOutUseCase: SignOutUseCase,
) : ViewModel() {

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
        }
    }
}