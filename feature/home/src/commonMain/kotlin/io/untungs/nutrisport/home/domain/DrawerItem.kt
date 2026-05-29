package io.untungs.nutrisport.home.domain

import androidx.compose.ui.graphics.vector.ImageVector
import io.untungs.nutrisport.core.ui.icons.Book
import io.untungs.nutrisport.core.ui.icons.Edit
import io.untungs.nutrisport.core.ui.icons.Icons
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
        icon = Icons.User
    ),
    Blog(
        title = "Blog",
        icon = Icons.Book
    ),
    Locations(
        title = "Locations",
        icon = Icons.MapPin
    ),
    Contact(
        title = "Contact us",
        icon = Icons.Edit
    ),
    SignOut(
        title = "Sign out",
        icon = Icons.LogOut
    ),
    Admin(
        title = "Admin",
        icon = Icons.Unlock
    ),
}