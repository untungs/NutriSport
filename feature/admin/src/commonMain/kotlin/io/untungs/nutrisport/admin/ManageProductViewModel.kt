package io.untungs.nutrisport.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.admin.view.ManageProductFormAction
import io.untungs.nutrisport.admin.view.ManageProductFormState
import io.untungs.nutrisport.core.domain.model.ProductCategory
import io.untungs.nutrisport.core.domain.usecase.GetProductUseCase
import io.untungs.nutrisport.core.domain.usecase.SubmitProductUseCase
import io.untungs.nutrisport.core.ui.AppMessageManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock

data class ManageProductState(
    val formState: ManageProductFormState = ManageProductFormState(),
    val isLoading: Boolean = false,
    val isSubmitting: Boolean = false,
    val errorMessage: String = ""
)

sealed interface ManageProductEvent {
    data object Success : ManageProductEvent
}

class ManageProductViewModel(
    val submitProductUseCase: SubmitProductUseCase,
    val getProductUseCase: GetProductUseCase,
    val appMessageManager: AppMessageManager,
) : ViewModel(), ManageProductFormAction {

    private val _state = MutableStateFlow(ManageProductState())
    val state: StateFlow<ManageProductState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<ManageProductEvent>()
    val event: SharedFlow<ManageProductEvent> = _event.asSharedFlow()

    fun fetchProduct(productId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            getProductUseCase(productId).collect { product ->
                if (product != null) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            formState = ManageProductFormState.fromProduct(product)
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Product not found"
                        )
                    }
                }
            }
        }
    }

    fun submitProduct() {
        viewModelScope.launch {
            _state.update { it.copy(isSubmitting = true) }

            val product = state.value.formState.toProduct(
                updatedAt = Clock.System.now().toEpochMilliseconds()
            )
            val result = submitProductUseCase(product)

            result.onSuccess {
                _state.update { it.copy(isSubmitting = false) }
                appMessageManager.showSuccess("Product successfully saved!")
                _event.emit(ManageProductEvent.Success)
            }.onFailure { e ->
                _state.update {
                    it.copy(
                        isSubmitting = false,
                        errorMessage = e.message ?: "Unknown error occurred"
                    )
                }
            }
        }
    }

    override fun onTitleChange(value: String) {
        _state.update {
            it.copy(formState = it.formState.copy(title = value, isTitleTouched = true))
        }
    }

    override fun onDescriptionChange(value: String) {
        _state.update {
            it.copy(formState = it.formState.copy(description = value, isDescriptionTouched = true))
        }
    }

    override fun onThumbnailChange(value: String) {
        _state.update { it.copy(formState = it.formState.copy(thumbnail = value)) }
    }

    override fun onCategoryChange(value: ProductCategory) {
        _state.update { it.copy(formState = it.formState.copy(category = value)) }
    }

    override fun onFlavorsChange(value: String) {
        _state.update { it.copy(formState = it.formState.copy(flavors = value)) }
    }

    override fun onWeightChange(value: Int?) {
        _state.update { it.copy(formState = it.formState.copy(weight = value)) }
    }

    override fun onPriceChange(value: Double) {
        _state.update {
            it.copy(formState = it.formState.copy(price = value, isPriceTouched = true))
        }
    }

    override fun onIsNewChange(value: Boolean) {
        _state.update { it.copy(formState = it.formState.copy(isNew = value)) }
    }

    override fun onIsPopularChange(value: Boolean) {
        _state.update { it.copy(formState = it.formState.copy(isPopular = value)) }
    }

    override fun onIsDiscountedChange(value: Boolean) {
        _state.update { it.copy(formState = it.formState.copy(isDiscounted = value)) }
    }
}
