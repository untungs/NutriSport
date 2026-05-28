package io.untungs.nutrisport.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.model.PhoneNumber
import io.untungs.nutrisport.core.domain.usecase.GetCustomerUseCase
import io.untungs.nutrisport.core.domain.usecase.UpdateCustomerUseCase
import io.untungs.nutrisport.core.ui.AppMessageManager
import io.untungs.nutrisport.profile.view.ProfileFormAction
import io.untungs.nutrisport.profile.view.ProfileFormState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileScreenState(
    val id: String = "",
    val formState: ProfileFormState = ProfileFormState(),
    val isLoading: Boolean = false,
    val isUpdating: Boolean = false,
    val errorMessage: String = "",
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
            _state.update { it.copy(isLoading = true) }

            getCustomerUseCase().collect { result ->
                result.onSuccess { data ->
                    _state.update {
                        it.copy(
                            id = data.id,
                            formState = ProfileFormState(
                                displayName = data.displayName,
                                email = data.email,
                                city = data.city,
                                postalCode = data.postalCode,
                                address = data.address,
                                country = Country.fromDialCode(data.phoneNumber?.dialCode ?: 62),
                                phoneNumber = data.phoneNumber?.number.orEmpty(),
                            ),
                            isLoading = false,
                            errorMessage = ""
                        )
                    }
                }.onFailure { exception ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "Failed to load profile"
                        )
                    }
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
                    number = currentState.formState.phoneNumber
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
        _state.update { it.copy(formState = it.formState.copy(displayName = value)) }
    }

    override fun onCityChange(value: String) {
        _state.update { it.copy(formState = it.formState.copy(city = value)) }
    }

    override fun onPostalCodeChange(value: Int?) {
        _state.update { it.copy(formState = it.formState.copy(postalCode = value)) }
    }

    override fun onAddressChange(value: String) {
        _state.update { it.copy(formState = it.formState.copy(address = value)) }
    }

    override fun onCountrySelected(value: Country) {
        _state.update { it.copy(formState = it.formState.copy(country = value)) }
    }

    override fun onPhoneNumberChange(value: String) {
        _state.update { it.copy(formState = it.formState.copy(phoneNumber = value)) }
    }
}
