package io.untungs.nutrisport.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.component.CustomProgressIndicator
import io.untungs.nutrisport.core.ui.component.InfoCard
import io.untungs.nutrisport.core.ui.component.PrimaryButton
import io.untungs.nutrisport.core.ui.component.PrimaryTopAppBar
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

@Composable
private fun ProfileScreen(
    state: ProfileScreenState,
    action: ProfileFormAction,
    onUpdateClick: () -> Unit,
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            PrimaryTopAppBar(
                title = "My Profile",
                onBackClick = navigateBack
            )
        },
        // safeDrawing ensures innerPadding includes status/nav bars, camera cutouts,
        // and importantly, the keyboard height (IME) when it's open.
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CustomProgressIndicator()
                }
                state.errorMessage.isNotBlank() -> {
                    InfoCard(
                        modifier = Modifier.padding(24.dp),
                        title = "Oops!",
                        subtitle = state.errorMessage
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 24.dp, vertical = 12.dp)
                    ) {
                        ProfileForm(
                            state = state.formState,
                            action = action
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        PrimaryButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
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
