package io.untungs.nutrisport.core.ui

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

sealed interface AppMessage {
    data class Success(val message: String) : AppMessage
    data class Error(val message: String) : AppMessage
}

/**
 * A global message bus for displaying Toasts/Snackbars (via MessageBar) across the entire application.
 *
 * **Why use this?**
 * This manager is designed exclusively for background tasks and ViewModels that transcend individual
 * screen lifecycles (e.g., a network call that finishes after the user has navigated away).
 * Firing messages through this bus guarantees the message will be rendered regardless of which
 * screen is currently active in Compose.
 *
 * **When NOT to use this:**
 * If you are triggering a message purely from a synchronous UI interaction that does not involve
 * a background scope (e.g., clicking a button to copy text), you should instead use a standard
 * `CompositionLocal` approach to avoid unnecessary DI coupling.
 */
class AppMessageManager {
    private val _messages = MutableSharedFlow<AppMessage>(extraBufferCapacity = 1)
    val messages: SharedFlow<AppMessage> = _messages.asSharedFlow()

    fun showSuccess(message: String) {
        _messages.tryEmit(AppMessage.Success(message))
    }

    fun showError(message: String) {
        _messages.tryEmit(AppMessage.Error(message))
    }
}
