package io.untungs.nutrisport.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.component.ErrorCard
import io.untungs.nutrisport.core.ui.component.PrimaryButton
import io.untungs.nutrisport.core.ui.icons.BackArrow
import io.untungs.nutrisport.core.ui.icons.Check
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import io.untungs.nutrisport.profile.view.ProfileForm
import io.untungs.nutrisport.profile.view.ProfileFormAction
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        action = viewModel,
        onUpdateClick = viewModel::updateProfile,
        navigateBack = navigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreen(
    state: ProfileScreenState,
    action: ProfileFormAction,
    onUpdateClick: () -> Unit,
    navigateBack: () -> Unit,
) {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator()
                }
                state.errorMessage.isNotBlank() -> {
                    ErrorCard(
                        modifier = Modifier.padding(24.dp),
                        message = state.errorMessage
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        ProfileForm(
                            modifier = Modifier.weight(1f)
                                .padding(bottom = 12.dp),
                            state = state.formState,
                            action = action
                        )

                        PrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Update",
                            icon = Icons.Check,
                            enabled = state.formState.isFormValid,
                            isLoading = state.isUpdating
                        ) {
                            onUpdateClick()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    val mockAction = object : ProfileFormAction {
        override fun onDisplayNameChange(value: String) {}
        override fun onCityChange(value: String) {}
        override fun onPostalCodeChange(value: Int?) {}
        override fun onAddressChange(value: String) {}
        override fun onCountrySelected(value: Country) {}
        override fun onPhoneNumberChange(value: String) {}
    }

    NutriSportTheme {
        ProfileScreen(
            state = ProfileScreenState(),
            action = mockAction,
            onUpdateClick = {},
            navigateBack = {}
        )
    }
}
