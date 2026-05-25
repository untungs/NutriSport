package io.untungs.nutrisport.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.domain.model.PhoneNumber
import io.untungs.nutrisport.core.domain.usecase.GetCustomerUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileScreenState(
    val id: String = "",
    val displayName: String = "",
    val email: String = "",
    val city: String? = null,
    val postalCode: Int? = null,
    val address: String? = null,
    val country: Country = Country.INDONESIA,
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class ProfileViewModel(
    private val getCustomerUseCase: GetCustomerUseCase
): ViewModel() {

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
                            displayName = data.displayName,
                            email = data.email,
                            city = data.city,
                            postalCode = data.postalCode,
                            address = data.address,
                            country = Country.fromDialCode(data.phoneNumber?.dialCode ?: 62),
                            phoneNumber = data.phoneNumber?.number.orEmpty(),
                            isLoading = false,
                            errorMessage = null
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

    fun onDisplayNameChange(value: String) {
        _state.update { it.copy(displayName = value) }
    }

    fun onCityChange(value: String) {
        _state.update { it.copy(city = value) }
    }

    fun onPostalCodeChange(value: Int?) {
        _state.update { it.copy(postalCode = value) }
    }

    fun onAddressChange(value: String) {
        _state.update { it.copy(address = value) }
    }

    fun onCountrySelected(value: Country) {
        _state.update { it.copy(country = value) }
    }

    fun onPhoneNumberChange(value: String) {
        _state.update { it.copy(phoneNumber = value) }
    }
}