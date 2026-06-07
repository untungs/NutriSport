package io.untungs.nutrisport.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.usecase.GetProductsUseCase
import io.untungs.nutrisport.core.domain.util.DataState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn

class AdminPanelViewModel(
    getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _searchBarVisible = MutableStateFlow(false)
    val searchBarVisible = _searchBarVisible.asStateFlow()

    @OptIn(FlowPreview::class)
    val state: StateFlow<DataState<List<Product>>> = combine(
        getProductsUseCase(),
        _searchQuery.debounce { if (it.isEmpty()) 0L else 300L }
    ) { productsState, query ->
        productsState.map { products ->
            if (query.isBlank()) products
            else products.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DataState.Loading
    )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun updateSearchBarVisible(visible: Boolean) {
        _searchBarVisible.value = visible
        if (!visible) {
            updateSearchQuery("")
        }
    }
}
