package io.untungs.nutrisport

import androidx.compose.ui.window.ComposeUIViewController
import io.untungs.nutrisport.core.di.initializeKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initializeKoin() }
) { App() }