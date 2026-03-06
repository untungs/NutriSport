package io.untungs.nutrisport

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.core.navigation.SetupNavGraph
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    viewModel: AppViewModel = koinViewModel()
) {
    NutriSportTheme {
        val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

        startDestination?.let {
            SetupNavGraph(it)
        }
    }
}
