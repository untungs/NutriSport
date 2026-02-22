package io.untungs.nutrisport.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import io.untungs.nutrisport.shared.BebasNeueFont
import io.untungs.nutrisport.shared.FontSize
import io.untungs.nutrisport.shared.TextPrimary
import io.untungs.nutrisport.shared.TextSecondary
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
            errorMaxLines = 2
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
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )
                    Text(
                        text = "Sign in to continue",
                        modifier = Modifier.alpha(Alpha.HALF),
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary
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
    AuthScreen()
}