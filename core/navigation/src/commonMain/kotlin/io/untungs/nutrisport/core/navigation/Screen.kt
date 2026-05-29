package io.untungs.nutrisport.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Auth: Screen()
    @Serializable
    data object HomeGraph: Screen()

    // bottom bar
    @Serializable
    data object ProductsOverview: Screen()
    @Serializable
    data object Cart: Screen()
    @Serializable
    data object Categories: Screen()

    @Serializable
    data object Profile: Screen()
}