package io.untungs.nutrisport

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.untungs.nutrisport.navigation.SetupNavGraph
import io.untungs.nutrisport.shared.theme.NutriSportTheme

@Composable
@Preview
fun App() {
    NutriSportTheme {
        SetupNavGraph()
    }
}
