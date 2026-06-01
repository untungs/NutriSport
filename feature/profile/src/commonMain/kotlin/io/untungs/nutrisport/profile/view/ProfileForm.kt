package io.untungs.nutrisport.profile.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.component.CustomTextField

data class ProfileFormState(
    val displayName: String = "",
    val email: String = "",
    val country: Country = Country.INDONESIA,
    val city: String? = null,
    val postalCode: Int? = null,
    val address: String? = null,
    val phoneNumber: String = "",
) {
    val isDisplayNameValid: Boolean get() = displayName.length in 3..50
    val isCityValid: Boolean get() = city?.length in 3..50
    val isPostalCodeValid: Boolean get() = postalCode != null && postalCode.toString().length in 3..8
    val isAddressValid: Boolean get() = address?.length in 3..50
    val isPhoneNumberValid: Boolean get() = phoneNumber.length in 5..30

    val isFormValid: Boolean
        get() = isDisplayNameValid &&
                isCityValid &&
                isPostalCodeValid &&
                isAddressValid &&
                isPhoneNumberValid
}

interface ProfileFormAction {
    fun onDisplayNameChange(value: String)
    fun onCityChange(value: String)
    fun onPostalCodeChange(value: Int?)
    fun onAddressChange(value: String)
    fun onCountrySelected(value: Country)
    fun onPhoneNumberChange(value: String)
}

@Composable
fun ProfileForm(
    state: ProfileFormState,
    action: ProfileFormAction,
) {
    var showCountryDialog by remember { mutableStateOf(false) }

    if (showCountryDialog) {
        CountryPickerDialog(
            country = state.country,
            onConfirmClick = {
                action.onCountrySelected(it)
                showCountryDialog = false
            },
            onDismiss = { showCountryDialog = false }
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CustomTextField(
            value = state.displayName,
            onValueChange = action::onDisplayNameChange,
            placeholder = "Name",
            isError = !state.isDisplayNameValid
        )
        CustomTextField(
            value = state.email,
            onValueChange = {},
            placeholder = "Email",
            enabled = false
        )
        CustomTextField(
            value = state.city.orEmpty(),
            onValueChange = action::onCityChange,
            placeholder = "City",
            isError = !state.isCityValid
        )
        CustomTextField(
            value = state.postalCode?.toString().orEmpty(),
            onValueChange = { action.onPostalCodeChange(it.toIntOrNull()) },
            placeholder = "Postal Code",
            isError = !state.isPostalCodeValid,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        CustomTextField(
            value = state.address.orEmpty(),
            onValueChange = action::onAddressChange,
            placeholder = "Address",
            isError = !state.isAddressValid
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CustomTextField(
                modifier = Modifier.width(100.dp),
                value = "+${state.country.dialCode}",
                onValueChange = {},
                leadingIcon = {
                    CountryImage(
                        state.country,
                        modifier = Modifier.size(20.dp)
                    )
                },
                onClick = {
                    showCountryDialog = true
                }
            )
            CustomTextField(
                modifier = Modifier.weight(1f),
                value = state.phoneNumber,
                onValueChange = action::onPhoneNumberChange,
                placeholder = "Phone Number",
                isError = !state.isPhoneNumberValid,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }
}
