package io.untungs.nutrisport.profile.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.component.AlertTextField
import io.untungs.nutrisport.core.ui.component.CustomTextField

@Composable
fun ProfileForm(
    modifier: Modifier = Modifier,
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    email: String,
    country: Country,
    onCountrySelected: (Country) -> Unit,
    city: String?,
    onCityChange: (String) -> Unit,
    postalCode: Int?,
    onPostalCodeChange: (Int?) -> Unit,
    address: String?,
    onAddressChange: (String) -> Unit,
    phoneNumber: String?,
    onPhoneNumberChange: (String) -> Unit,
) {
    var showCountryDialog by remember { mutableStateOf(false) }

    if (showCountryDialog) {
        CountryPickerDialog(
            country = country,
            onConfirmClick = {
                onCountrySelected(it)
                showCountryDialog = false
            },
            onDismiss = { showCountryDialog = false }
        )
    }

    Column(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CustomTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            placeholder = "First Name",
            isError = firstName.length !in 3..50
        )
        CustomTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            placeholder = "Last Name",
            isError = lastName.length !in 3..50
        )
        CustomTextField(
            value = email,
            onValueChange = {},
            placeholder = "Email",
            enabled = false
        )
        CustomTextField(
            value = city.orEmpty(),
            onValueChange = onCityChange,
            placeholder = "City",
            isError = city?.length !in 3..50
        )
        CustomTextField(
            value = postalCode?.toString().orEmpty(),
            onValueChange = { onPostalCodeChange(it.toIntOrNull()) },
            placeholder = "Postal Code",
            isError = postalCode.toString().length !in 3..8
        )
        CustomTextField(
            value = address.orEmpty(),
            onValueChange = onAddressChange,
            placeholder = "Address",
            isError = address?.length !in 3..50
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AlertTextField(
                text = "+${country.dialCode}",
                icon = {
                    CountryImage(
                        country,
                        modifier = Modifier.size(14.dp)
                    )
                },
                onClick = {
                    showCountryDialog = true
                }
            )
            CustomTextField(
                value = phoneNumber.orEmpty(),
                onValueChange = onPhoneNumberChange,
                placeholder = "Phone Number",
                isError = phoneNumber?.length !in 5..30
            )
        }
    }
}