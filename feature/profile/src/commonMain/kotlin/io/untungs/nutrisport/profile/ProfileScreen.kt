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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.core.ui.component.PrimaryButton
import io.untungs.nutrisport.core.ui.icons.BackArrow
import io.untungs.nutrisport.core.ui.icons.Check
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import io.untungs.nutrisport.profile.view.ProfileForm
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                displayName = state.displayName,
                onDisplayNameChange = viewModel::onDisplayNameChange,
                email = state.email,
                country = state.country,
                onCountrySelected = viewModel::onCountrySelected,
                city = state.city,
                onCityChange = viewModel::onCityChange,
                postalCode = state.postalCode,
                onPostalCodeChange = viewModel::onPostalCodeChange,
                address = state.address,
                onAddressChange = viewModel::onAddressChange,
                phoneNumber = state.phoneNumber,
                onPhoneNumberChange = viewModel::onPhoneNumberChange,
            )

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Update",
                icon = Icons.Check,
                enabled = !state.isLoading
            ) {

            }
        }
    }
}

@Composable
private fun ProfileScreenPreview() {
    NutriSportTheme {
        ProfileScreen(navigateBack = {})
    }
}