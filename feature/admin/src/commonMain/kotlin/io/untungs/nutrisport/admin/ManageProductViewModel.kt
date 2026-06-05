package io.untungs.nutrisport.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.admin.view.ManageProductFormAction
import io.untungs.nutrisport.admin.view.ManageProductFormState
import io.untungs.nutrisport.core.domain.model.ProductCategory
import io.untungs.nutrisport.core.domain.usecase.GetProductUseCase
import io.untungs.nutrisport.core.domain.usecase.SubmitProductUseCase
import io.untungs.nutrisport.core.domain.usecase.UploadProductImageUseCase
import io.untungs.nutrisport.core.domain.usecase.DeleteProductImageUseCase
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
    data object NavigateBack : ManageProductEvent
}

class ManageProductViewModel(
    val submitProductUseCase: SubmitProductUseCase,
    val getProductUseCase: GetProductUseCase,
    val uploadProductImageUseCase: UploadProductImageUseCase,
    val deleteProductImageUseCase: DeleteProductImageUseCase,
    val appMessageManager: AppMessageManager,
) : ViewModel(), ManageProductFormAction {

    private val _state = MutableStateFlow(ManageProductState())
    val state: StateFlow<ManageProductState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<ManageProductEvent>()
    val event: SharedFlow<ManageProductEvent> = _event.asSharedFlow()

    fun fetchProduct(productId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = "") }

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

            val formState = state.value.formState
            val product = formState.toProduct(
                updatedAt = Clock.System.now().toEpochMilliseconds()
            )
            val result = submitProductUseCase(product)

            result.onSuccess {
                _state.update { it.copy(isSubmitting = false) }

                // Cleanup unused images
                if (product.thumbnail != formState.oldThumbnail) {
                    deleteProductImageUseCase(formState.oldThumbnail)
                }

                appMessageManager.showSuccess("Product successfully saved!")
                _event.emit(ManageProductEvent.NavigateBack)
            }.onFailure { e ->
                _state.update { it.copy(isSubmitting = false) }
                appMessageManager.showError(e.message ?: "Unknown error occurred")
            }
        }
    }

    fun uploadImage(bytes: ByteArray) {
        viewModelScope.launch {
            updateFormState { it.copy(isImageUploading = true) }

            val stateBeforeUpload = state.value.formState
            val result = uploadProductImageUseCase(stateBeforeUpload.id, bytes)

            result.onSuccess { url ->
                updateFormState {
                    it.copy(thumbnail = url, newThumbnail = url, isImageUploading = false)
                }
                // Delete previous newThumbnail
                deleteProductImageUseCase(stateBeforeUpload.newThumbnail)
            }.onFailure { e ->
                updateFormState { it.copy(isImageUploading = false) }
                appMessageManager.showError(e.message ?: "Failed to upload image")
            }
        }
    }

    fun cancelEdit() {
        viewModelScope.launch {
            deleteProductImageUseCase(state.value.formState.newThumbnail)
            _event.emit(ManageProductEvent.NavigateBack)
        }
    }

    override fun onTitleChange(value: String) {
        updateFormState { it.copy(title = value, isTitleTouched = true) }
    }

    override fun onDescriptionChange(value: String) {
        updateFormState { it.copy(description = value, isDescriptionTouched = true) }
    }

    override fun onDeleteImageClick() {
        viewModelScope.launch {
            val currentNewThumbnail = state.value.formState.newThumbnail
            updateFormState { it.copy(thumbnail = "", newThumbnail = "") }
            deleteProductImageUseCase(currentNewThumbnail)
        }
    }

    override fun onCategoryChange(value: ProductCategory) {
        updateFormState { it.copy(category = value) }
    }

    override fun onFlavorsChange(value: String) {
        updateFormState { it.copy(flavors = value) }
    }

    override fun onWeightChange(value: Int?) {
        updateFormState { it.copy(weight = value) }
    }

    override fun onPriceChange(value: Double) {
        updateFormState { it.copy(price = value, isPriceTouched = true) }
    }

    override fun onIsNewChange(value: Boolean) {
        updateFormState { it.copy(isNew = value) }
    }

    override fun onIsPopularChange(value: Boolean) {
        updateFormState { it.copy(isPopular = value) }
    }

    override fun onIsDiscountedChange(value: Boolean) {
        updateFormState { it.copy(isDiscounted = value) }
    }

    private fun updateFormState(update: (ManageProductFormState) -> ManageProductFormState) {
        _state.update { it.copy(formState = update(it.formState)) }
    }
}
