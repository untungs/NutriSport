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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.auth.component.GoogleButton
import io.untungs.nutrisport.shared.Alpha
import io.untungs.nutrisport.shared.theme.NutriSportTheme
import rememberMessageBarState

@Composable
fun AuthScreen(modifier: Modifier = Modifier) {
    val messageBarState = rememberMessageBarState()

    Scaffold(modifier = modifier) { padding ->
        ContentWithMessageBar(
            messageBarState = messageBarState,
            errorMaxLines = 2
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    )
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

                GoogleButton(modifier = Modifier.fillMaxWidth()) {

                }
            }
        }
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