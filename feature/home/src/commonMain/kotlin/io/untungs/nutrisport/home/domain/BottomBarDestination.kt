package io.untungs.nutrisport.home.domain

import androidx.compose.ui.graphics.vector.ImageVector
import io.untungs.nutrisport.core.navigation.Screen
import io.untungs.nutrisport.core.ui.icons.Grid
import io.untungs.nutrisport.core.ui.icons.Home
import io.untungs.nutrisport.core.ui.icons.Icon
import io.untungs.nutrisport.core.ui.icons.ShoppingCart

enum class BottomBarDestination(
    val icon: ImageVector,
    val title: String,
    val screen: Screen
) {
    ProductsOverview(
        icon = Icon.Home,
        title = "Nutri Sport",
        screen = Screen.ProductsOverview
    ),
    Cart(
        icon = Icon.ShoppingCart,
        title = "Cart",
        screen = Screen.Cart
    ),
    Categories(
        icon = Icon.Grid,
        title = "Categories",
        screen = Screen.Categories
    ),
}
