package io.untungs.nutrisport.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.usecase.GetProductsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class AdminPanelState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = true
)

class AdminPanelViewModel(
    getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    val state: StateFlow<AdminPanelState> = getProductsUseCase()
        .map { products ->
            AdminPanelState(
                products = products.sortedByDescending { it.createdAt },
                isLoading = false
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AdminPanelState()
        )
}
