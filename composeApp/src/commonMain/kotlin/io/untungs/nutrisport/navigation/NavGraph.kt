package io.untungs.nutrisport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.untungs.nutrisport.auth.AuthRoute
import io.untungs.nutrisport.core.navigation.Screen
import io.untungs.nutrisport.home.HomeGraphRoute

@Composable
fun SetupNavGraph(startDestination: Screen = Screen.Auth) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Auth> {
            AuthRoute()
        }
        composable<Screen.HomeGraph> {
            HomeGraphRoute()
        }
    }
}
