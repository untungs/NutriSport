package io.untungs.nutrisport.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.model.PhoneNumber
import io.untungs.nutrisport.core.domain.usecase.GetCustomerUseCase
import io.untungs.nutrisport.core.domain.usecase.UpdateCustomerUseCase
import io.untungs.nutrisport.core.domain.util.DataState
import io.untungs.nutrisport.core.ui.AppMessageManager
import io.untungs.nutrisport.profile.view.ProfileFormAction
import io.untungs.nutrisport.profile.view.ProfileFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileScreenState(
    val id: String = "",
    val profile: DataState<Customer?> = DataState.Success(null),
    val formState: ProfileFormState = ProfileFormState(),
    val isUpdating: Boolean = false,
)

class ProfileViewModel(
    private val getCustomerUseCase: GetCustomerUseCase,
    private val updateCustomerUseCase: UpdateCustomerUseCase,
    private val appMessageManager: AppMessageManager,
): ViewModel(), ProfileFormAction {

    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            getCustomerUseCase().collect { dataState ->
                val formState = dataState.getOrNull()?.let {
                    ProfileFormState.fromCustomer(it)
                }

                _state.update {
                    it.copy(
                        id = dataState.getOrNull()?.id ?: it.id,
                        profile = dataState,
                        formState = formState ?: it.formState
                    )
                }
            }
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            val currentState = _state.value
            _state.update { it.copy(isUpdating = true) }

            val customer = Customer(
                id = currentState.id,
                displayName = currentState.formState.displayName,
                email = currentState.formState.email,
                city = currentState.formState.city,
                postalCode = currentState.formState.postalCode,
                address = currentState.formState.address,
                phoneNumber = PhoneNumber(
                    dialCode = currentState.formState.country.dialCode,
                    number = currentState.formState.phoneNumber,
                )
            )

            updateCustomerUseCase(customer).onSuccess {
                _state.update { it.copy(isUpdating = false) }
                appMessageManager.showSuccess("Profile updated successfully")
            }.onFailure { exception ->
                _state.update { it.copy(isUpdating = false) }
                appMessageManager.showError(exception.message ?: "Failed to update profile")
            }
        }
    }

    override fun onDisplayNameChange(value: String) {
        updateFormState { it.copy(displayName = value) }
    }

    override fun onCityChange(value: String) {
        updateFormState { it.copy(city = value) }
    }

    override fun onPostalCodeChange(value: Int?) {
        updateFormState { it.copy(postalCode = value) }
    }

    override fun onAddressChange(value: String) {
        updateFormState { it.copy(address = value) }
    }

    override fun onCountrySelected(value: Country) {
        updateFormState { it.copy(country = value) }
    }

    override fun onPhoneNumberChange(value: String) {
        updateFormState { it.copy(phoneNumber = value) }
    }

    private fun updateFormState(update: (ProfileFormState) -> ProfileFormState) {
        _state.update { it.copy(formState = update(it.formState)) }
    }
}
