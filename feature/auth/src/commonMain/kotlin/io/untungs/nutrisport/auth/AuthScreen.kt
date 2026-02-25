package io.untungs.nutrisport.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import dev.gitlive.firebase.auth.FirebaseUser
import io.untungs.nutrisport.auth.component.GoogleButton
import io.untungs.nutrisport.core.ui.Alpha
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import rememberMessageBarState

@Composable
fun AuthScreen(modifier: Modifier = Modifier) {
    val messageBarState = rememberMessageBarState()

    Scaffold(modifier = modifier) { padding ->
        ContentWithMessageBar(
            modifier = Modifier
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                ),
            messageBarState = messageBarState,
            errorMaxLines = 2,
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

                SignInButton { result ->
                    result.onSuccess { user ->
                        if (user != null) {
                            messageBarState.addSuccess("Signed in successfully")
                        }
                    }.onFailure {
                        messageBarState.addError(it.message ?: "Something went wrong")
                    }
                }
            }
        }
    }
}

@Composable
private fun SignInButton(onResult: (Result<FirebaseUser?>) -> Unit) {
    var isLoading by remember { mutableStateOf(false) }

    GoogleButtonUiContainerFirebase(
        linkAccount = false,
        onResult = { result ->
            isLoading = false

            result.onSuccess {
                onResult(Result.success(it))
            }.onFailure {
                if (it.message == "Idtoken is null") {
                    // sign in is cancelled, do nothing
                    onResult(Result.success(null))
                } else {
                    onResult(Result.failure(it))
                }
            }
        }
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

@Preview
@Composable
private fun AuthScreenPreview() {
    NutriSportTheme {
        AuthScreen()
    }
}

@Preview
@Composable
private fun AuthScreenPreviewDark() {
    NutriSportTheme(darkTheme = true) {
        AuthScreen()
    }
}