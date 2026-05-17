package io.untungs.nutrisport.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.untungs.nutrisport.core.domain.usecase.SignOutUseCase
import io.untungs.nutrisport.core.navigation.Screen
import io.untungs.nutrisport.core.ui.icons.Icon
import io.untungs.nutrisport.core.ui.icons.Menu
import io.untungs.nutrisport.home.component.BottomBar
import io.untungs.nutrisport.home.domain.BottomBarDestination
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGraphScreen(
    modifier: Modifier = Modifier,
    signOutUseCase: SignOutUseCase = koinInject()
) {
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
    val selectedDestination by remember {
        derivedStateOf {
            val route = currentRoute.value?.destination?.route.orEmpty()
            BottomBarDestination.entries.find {
                route.contains(it.name, ignoreCase = true)
            } ?: BottomBarDestination.ProductsOverview
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            Box(
                modifier = Modifier.padding(12.dp)
            ) {
                BottomBar(
                    selected = selectedDestination,
                    onSelect = { destination ->
                        navController.navigate(destination.screen) {
                            launchSingleTop = true
                            popUpTo<Screen.ProductsOverview> {
                                saveState = true
                                inclusive = false
                            }
                            restoreState = true
                        }
                    }
                )
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                windowInsets = WindowInsets(0, 0, 0, 0),
                title = {
                    AnimatedContent(selectedDestination) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icon.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            NavHost(
                navController = navController,
                startDestination = Screen.ProductsOverview,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Screen.ProductsOverview> {
                    Column {
                        Button(onClick = {
                            coroutineScope.launch {
                                signOutUseCase()
                            }
                        }) {
                            Text("Temporary Sign Out")
                        }
                    }
                }

                composable<Screen.Cart> { }

                composable<Screen.Categories> { }
            }
        }
    }
}
