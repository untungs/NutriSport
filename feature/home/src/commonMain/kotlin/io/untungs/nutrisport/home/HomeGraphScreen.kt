package io.untungs.nutrisport.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.untungs.nutrisport.core.navigation.Screen
import io.untungs.nutrisport.core.ui.Alpha
import io.untungs.nutrisport.core.ui.icons.Close
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.icons.Menu
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import io.untungs.nutrisport.home.component.BottomBar
import io.untungs.nutrisport.home.component.CustomDrawer
import io.untungs.nutrisport.home.domain.BottomBarDestination
import io.untungs.nutrisport.home.domain.CustomDrawerState
import io.untungs.nutrisport.home.domain.isOpened
import io.untungs.nutrisport.home.domain.toggle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeGraphRoute(
    viewModel: HomeGraphViewModel = koinViewModel(),
    onProfileClick: () -> Unit,
    onAdminPanelClick: () -> Unit,
) {
    HomeGraphScreen(
        onProfileClick = onProfileClick,
        onAdminPanelClick = onAdminPanelClick,
        onSignOutClick = viewModel::signOut,
    )
}

@Composable
fun HomeGraphScreen(
    onProfileClick: () -> Unit,
    onAdminPanelClick: () -> Unit,
    onSignOutClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceBright)
    ) {
        val screenWidth = maxWidth
        var drawerState by remember { mutableStateOf(CustomDrawerState.Closed) }

        val offsetValue = (screenWidth / 1.5f).coerceAtMost(300.dp)
        val animatedOffset by animateDpAsState(
            targetValue = if (drawerState.isOpened()) offsetValue else 0.dp
        )
        val animatedRadius by animateDpAsState(
            targetValue = if (drawerState.isOpened()) 20.dp else 0.dp
        )
        val animatedScale by animateFloatAsState(
            targetValue = if (drawerState.isOpened()) 0.9f else 1f
        )

        CustomDrawer(
            modifier = Modifier
                .width(offsetValue)
                .safeDrawingPadding(),
            onProfileClick = onProfileClick,
            onSignOutClick = onSignOutClick,
            onAdminPanelClick = onAdminPanelClick,
        )

        Box(
            modifier = Modifier.fillMaxSize()
                .graphicsLayer {
                    translationX = animatedOffset.toPx()
                    scaleX = animatedScale
                    scaleY = animatedScale
                    shadowElevation = 20.dp.toPx()
                    shape = RoundedCornerShape(animatedRadius)
                    clip = true
                    ambientShadowColor = Color.Black.copy(alpha = Alpha.TEN_PERCENT)
                    spotShadowColor = Color.Black.copy(alpha = Alpha.TEN_PERCENT)
                }
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
private fun ContentScaffold(
    drawerState: CustomDrawerState,
    onMenuClick: () -> Unit,
) {
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
        bottomBar = {
            Box(
                modifier = Modifier.padding(12.dp)
                    .safeDrawingPadding()
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
                            imageVector = if (drawerState.isOpened()) Icons.Close else Icons.Menu,
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
            composable<Screen.ProductsOverview> {}

            composable<Screen.Cart> { }

            composable<Screen.Categories> { }
        }
    }
}

@Preview
@Composable
private fun HomeGraphScreenPreview() {
    NutriSportTheme(darkTheme = true) {
        HomeGraphScreen(
            onProfileClick = {},
            onAdminPanelClick = {},
            onSignOutClick = {}
        )
    }
}