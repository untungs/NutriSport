package io.untungs.nutrisport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.untungs.nutrisport.admin.AdminPanelRoute
import io.untungs.nutrisport.admin.ManageProductRoute
import io.untungs.nutrisport.auth.AuthRoute
import io.untungs.nutrisport.core.navigation.Screen
import io.untungs.nutrisport.home.HomeGraphRoute
import io.untungs.nutrisport.profile.ProfileRoute

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
            HomeGraphRoute(
                onProfileClick = {
                    navController.navigate(Screen.Profile)
                },
                onAdminPanelClick = {
                    navController.navigate(Screen.AdminPanel)
                }
            )
        }
        composable<Screen.Profile> {
            ProfileRoute(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<Screen.AdminPanel> {
            AdminPanelRoute(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToManageProduct = {
                    navController.navigate(Screen.ManageProduct(productId = it))
                }
            )
        }
        composable<Screen.ManageProduct> {
            val productId = it.toRoute<Screen.ManageProduct>().productId
            ManageProductRoute(
                productId = productId,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
