package io.untungs.nutrisport.admin

import androidx.lifecycle.ViewModel
import io.untungs.nutrisport.admin.view.ManageProductFormAction
import io.untungs.nutrisport.admin.view.ManageProductFormState
import io.untungs.nutrisport.core.domain.model.ProductCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class ManageProductUiState(
    val formState: ManageProductFormState = ManageProductFormState(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)

class ManageProductViewModel : ViewModel(), ManageProductFormAction {

    private val _uiState = MutableStateFlow(ManageProductUiState())
    val uiState: StateFlow<ManageProductUiState> = _uiState.asStateFlow()

    override fun onTitleChange(value: String) {
        _uiState.update { it.copy(formState = it.formState.copy(title = value)) }
    }

    override fun onDescriptionChange(value: String) {
        _uiState.update { it.copy(formState = it.formState.copy(description = value)) }
    }

    override fun onThumbnailChange(value: String) {
        _uiState.update { it.copy(formState = it.formState.copy(thumbnail = value)) }
    }

    override fun onCategoryChange(value: ProductCategory) {
        _uiState.update { it.copy(formState = it.formState.copy(category = value)) }
    }

    override fun onFlavorsChange(value: String) {
        _uiState.update { it.copy(formState = it.formState.copy(flavors = value)) }
    }

    override fun onWeightChange(value: Int?) {
        _uiState.update { it.copy(formState = it.formState.copy(weight = value)) }
    }

    override fun onPriceChange(value: Double) {
        _uiState.update { it.copy(formState = it.formState.copy(price = value)) }
    }

    override fun onIsNewChange(value: Boolean) {
        _uiState.update { it.copy(formState = it.formState.copy(isNew = value)) }
    }

    override fun onIsPopularChange(value: Boolean) {
        _uiState.update { it.copy(formState = it.formState.copy(isPopular = value)) }
    }

    override fun onIsDiscountedChange(value: Boolean) {
        _uiState.update { it.copy(formState = it.formState.copy(isDiscounted = value)) }
    }
}
