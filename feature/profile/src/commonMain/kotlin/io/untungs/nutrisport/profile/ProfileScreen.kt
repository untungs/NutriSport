package io.untungs.nutrisport.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import io.untungs.nutrisport.profile.view.ProfileForm

@Composable
fun ProfileScreen() {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var country by remember { mutableStateOf(Country.INDONESIA) }
    var city by remember { mutableStateOf("") }
    var postalCode: Int? by remember { mutableStateOf(0) }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            ProfileForm(
                firstName = firstName,
                onFirstNameChange = { firstName = it },
                lastName = lastName,
                onLastNameChange = { lastName = it },
                email = email,
                country = country,
                onCountrySelected = { country = it },
                city = city,
                onCityChange = { city = it },
                postalCode = postalCode,
                onPostalCodeChange = { postalCode = it },
                address = address,
                onAddressChange = { address = it },
                phoneNumber = phoneNumber,
                onPhoneNumberChange = { phoneNumber = it },
            )
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    NutriSportTheme {
        ProfileScreen()
    }
}