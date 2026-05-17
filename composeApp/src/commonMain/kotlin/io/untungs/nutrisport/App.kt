package io.untungs.nutrisport

import ContentWithMessageBar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.surface,
        ) { padding ->
            ContentWithMessageBar(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = padding.calculateTopPadding(),
                        bottom = padding.calculateBottomPadding()
                    ),
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
}
