package io.untungs.nutrisport.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.usecase.GetCustomerUseCase
import io.untungs.nutrisport.core.domain.usecase.SignOutUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeGraphViewModel(
    private val signOutUseCase: SignOutUseCase,
    getCustomerUseCase: GetCustomerUseCase,
) : ViewModel() {

    val isAdmin: StateFlow<Boolean> = getCustomerUseCase()
        .map { it.getOrNull()?.isAdmin ?: false }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false,
        )

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
        }
    }
}