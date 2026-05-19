package io.untungs.nutrisport

import ContentWithMessageBar
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.navigation.SetupNavGraph
import io.untungs.nutrisport.core.ui.AppMessage
import io.untungs.nutrisport.core.ui.AppMessageManager
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import rememberMessageBarState

@Composable
fun App(
    viewModel: AppViewModel = koinViewModel(),
    messageManager: AppMessageManager = koinInject()
) {
    NutriSportTheme {
        val messageBarState = rememberMessageBarState()
        val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

        LaunchedEffect(messageManager) {
            messageManager.messages.collect { message ->
                when (message) {
                    is AppMessage.Success -> messageBarState.addSuccess(message.message)
                    is AppMessage.Error -> messageBarState.addError(message.message)
                }
            }
        }

        ContentWithMessageBar(
            modifier = Modifier.fillMaxSize(),
            visibilityOffset = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
            messageBarState = messageBarState,
            errorMaxLines = 2
        ) {
            val destination = startDestination
            if (destination != null) {
                SetupNavGraph(destination)
            }
        }
    }
}
