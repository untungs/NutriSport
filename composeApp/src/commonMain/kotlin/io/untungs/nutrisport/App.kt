package io.untungs.nutrisport

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import io.untungs.nutrisport.core.navigation.SetupNavGraph
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme

@Composable
@Preview
fun App() {
    NutriSportTheme {
        var isReady by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            GoogleAuthProvider.create(GoogleAuthCredentials(Constants.GOOGLE_WEB_KEY))
            isReady = true
        }

        AnimatedVisibility(isReady, modifier = Modifier.fillMaxSize()) {
            SetupNavGraph()
        }
    }
}
