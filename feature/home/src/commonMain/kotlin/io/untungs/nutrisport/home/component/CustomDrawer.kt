package io.untungs.nutrisport.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.home.domain.DrawerItem

@Composable
fun CustomDrawer(
    modifier: Modifier = Modifier,
    onProfileClick: () -> Unit = {},
    onContactUsClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {},
    onAdminPanelClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)
            .padding(horizontal = 12.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "NUTRISPORT",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Healthy Lifestyle",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(50.dp))

        DrawerItem.entries.forEach {
            if (it == DrawerItem.Admin) {
                Spacer(modifier = Modifier.weight(1f))

                DrawerItemCard(it, onAdminPanelClick)
            } else {
                DrawerItemCard(it) {
                    when (it) {
                        DrawerItem.Profile -> onProfileClick()
                        DrawerItem.Blog -> {}
                        DrawerItem.Locations -> {}
                        DrawerItem.Contact -> onContactUsClick()
                        DrawerItem.SignOut -> onSignOutClick()
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
