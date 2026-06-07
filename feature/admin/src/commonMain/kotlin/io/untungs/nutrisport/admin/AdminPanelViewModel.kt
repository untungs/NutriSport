package io.untungs.nutrisport.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.usecase.GetProductsUseCase
import io.untungs.nutrisport.core.domain.util.DataState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class AdminPanelViewModel(
    getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    val state: StateFlow<DataState<List<Product>>> = getProductsUseCase()
        .map { state -> state.map { it.sortedByDescending { product -> product.createdAt } } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DataState.Loading
        )
}
