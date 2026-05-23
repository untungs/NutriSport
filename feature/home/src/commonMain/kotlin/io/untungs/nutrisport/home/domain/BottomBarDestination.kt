package io.untungs.nutrisport.home.domain

import androidx.compose.ui.graphics.vector.ImageVector
import io.untungs.nutrisport.core.navigation.Screen
import io.untungs.nutrisport.core.ui.icons.Grid
import io.untungs.nutrisport.core.ui.icons.Home
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.icons.ShoppingCart

enum class BottomBarDestination(
    val icon: ImageVector,
    val title: String,
    val screen: Screen
) {
    ProductsOverview(
        icon = Icons.Home,
        title = "Nutri Sport",
        screen = Screen.ProductsOverview
    ),
    Cart(
        icon = Icons.ShoppingCart,
        title = "Cart",
        screen = Screen.Cart
    ),
    Categories(
        icon = Icons.Grid,
        title = "Categories",
        screen = Screen.Categories
    ),
}
