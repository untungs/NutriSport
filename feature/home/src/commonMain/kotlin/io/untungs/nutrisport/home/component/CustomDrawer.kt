package io.untungs.nutrisport.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    isAdmin: Boolean = false,
    onProfileClick: () -> Unit = {},
    onContactUsClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {},
    onAdminPanelClick: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(horizontal = 12.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
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

            DrawerItem.entries.filter { it != DrawerItem.Admin }.forEach {
                DrawerItemCard(it) {
                    when (it) {
                        DrawerItem.Profile -> onProfileClick()
                        DrawerItem.Blog -> {}
                        DrawerItem.Locations -> {}
                        DrawerItem.Contact -> onContactUsClick()
                        DrawerItem.SignOut -> onSignOutClick()
                        else -> {}
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        if (isAdmin) {
            DrawerItemCard(DrawerItem.Admin, onAdminPanelClick)
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
