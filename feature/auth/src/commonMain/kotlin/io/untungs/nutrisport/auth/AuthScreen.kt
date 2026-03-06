package io.untungs.nutrisport.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import dev.gitlive.firebase.auth.FirebaseUser
import io.untungs.nutrisport.auth.component.GoogleButton
import io.untungs.nutrisport.core.ui.Alpha
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AuthRoute(
    viewModel: AuthViewModel = koinViewModel()
) {
    AuthScreen(
        onSignInResult = viewModel::onSignInResult
    )
}

@Composable
fun AuthScreen(
    onSignInResult: (Result<FirebaseUser?>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "NUTRISPORT",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Sign in to continue",
                modifier = Modifier.alpha(Alpha.HALF),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        SignInButton(onResult = onSignInResult)
    }
}

@Composable
private fun SignInButton(onResult: (Result<FirebaseUser?>) -> Unit) {
    var isLoading by remember { mutableStateOf(false) }

    val handleResult: (Result<FirebaseUser?>) -> Unit = { result ->
        isLoading = false
        onResult(result)
    }

    if (LocalInspectionMode.current) {
        GoogleButton(
            loading = isLoading,
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )
    } else {
        GoogleButtonUiContainerFirebase(
            linkAccount = false,
            onResult = handleResult
        ) {
            GoogleButton(
                loading = isLoading,
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    isLoading = true
                    onClick()
                }
            )
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreview() {
    NutriSportTheme {
        Surface {
            AuthScreen(
                onSignInResult = {}
            )
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreviewDark() {
    NutriSportTheme(darkTheme = true) {
        Surface {
            AuthScreen(
                onSignInResult = {}
            )
        }
    }
}