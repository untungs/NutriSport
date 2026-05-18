package io.untungs.nutrisport.home.domain

import androidx.compose.ui.graphics.vector.ImageVector
import io.untungs.nutrisport.core.ui.icons.Book
import io.untungs.nutrisport.core.ui.icons.Edit
import io.untungs.nutrisport.core.ui.icons.Icon
import io.untungs.nutrisport.core.ui.icons.LogOut
import io.untungs.nutrisport.core.ui.icons.MapPin
import io.untungs.nutrisport.core.ui.icons.Unlock
import io.untungs.nutrisport.core.ui.icons.User

enum class DrawerItem(
    val title: String,
    val icon: ImageVector
) {
    Profile(
        title = "Profile",
        icon = Icon.User
    ),
    Blog(
        title = "Blog",
        icon = Icon.Book
    ),
    Locations(
        title = "Locations",
        icon = Icon.MapPin
    ),
    Contact(
        title = "Contact us",
        icon = Icon.Edit
    ),
    SignOut(
        title = "Sign out",
        icon = Icon.LogOut
    ),
    Admin(
        title = "Admin",
        icon = Icon.Unlock
    ),
}