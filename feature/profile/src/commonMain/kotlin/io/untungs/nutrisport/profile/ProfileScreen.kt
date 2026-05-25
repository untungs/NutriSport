package io.untungs.nutrisport.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.component.PrimaryButton
import io.untungs.nutrisport.core.ui.icons.BackArrow
import io.untungs.nutrisport.core.ui.icons.Check
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import io.untungs.nutrisport.profile.view.ProfileForm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit
) {
    var displayName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var country by remember { mutableStateOf(Country.INDONESIA) }
    var city by remember { mutableStateOf("") }
    var postalCode: Int? by remember { mutableStateOf(0) }
    var address by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("My Profile", style = MaterialTheme.typography.displayMedium)
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.BackArrow,
                            contentDescription = "Back"
                        )
                    }
                }
            )

        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            ProfileForm(
                modifier = Modifier.weight(1f),
                displayName = displayName,
                onDisplayNameChange = { displayName = it },
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

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Update",
                icon = Icons.Check,
            ) {

            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    NutriSportTheme {
        ProfileScreen {}
    }
}