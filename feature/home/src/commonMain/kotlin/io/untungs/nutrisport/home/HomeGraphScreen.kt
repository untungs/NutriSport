package io.untungs.nutrisport.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.untungs.nutrisport.core.domain.usecase.SignOutUseCase
import io.untungs.nutrisport.core.navigation.Screen
import io.untungs.nutrisport.core.ui.Alpha
import io.untungs.nutrisport.core.ui.icons.Close
import io.untungs.nutrisport.core.ui.icons.Icon
import io.untungs.nutrisport.core.ui.icons.Menu
import io.untungs.nutrisport.core.ui.util.getScreenWidth
import io.untungs.nutrisport.home.component.BottomBar
import io.untungs.nutrisport.home.component.CustomDrawer
import io.untungs.nutrisport.home.domain.BottomBarDestination
import io.untungs.nutrisport.home.domain.CustomDrawerState
import io.untungs.nutrisport.home.domain.isOpened
import io.untungs.nutrisport.home.domain.toggle
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun HomeGraphScreen() {
    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceBright)
    ) {
        val screenWidth = remember { getScreenWidth() }
        var drawerState by remember { mutableStateOf(CustomDrawerState.Closed) }

        val offsetValue by remember { derivedStateOf { (screenWidth / 1.5).dp } }
        val animatedOffset by animateDpAsState(
            targetValue = if (drawerState.isOpened()) offsetValue else 0.dp
        )
        val animatedRadius by animateDpAsState(
            targetValue = if (drawerState.isOpened()) 20.dp else 0.dp
        )
        val animatedScale by animateFloatAsState(
            targetValue = if (drawerState.isOpened()) 0.9f else 1f
        )

        CustomDrawer()
        Box(
            modifier = Modifier.fillMaxSize()
                .clip(RoundedCornerShape(animatedRadius))
                .offset(animatedOffset)
                .scale(animatedScale)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(animatedRadius),
                    ambientColor = Color.Black.copy(alpha = Alpha.TEN_PERCENT),
                    spotColor = Color.Black.copy(alpha = Alpha.TEN_PERCENT),
                )
        ) {
            ContentScaffold(
                drawerState = drawerState,
                onMenuClick = { drawerState = drawerState.toggle() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScaffold(
    drawerState: CustomDrawerState,
    onMenuClick: () -> Unit,
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
                    IconButton(onClick = onMenuClick) {
                        Icon(
                            imageVector = if (drawerState.isOpened()) Icon.Close else Icon.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
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
