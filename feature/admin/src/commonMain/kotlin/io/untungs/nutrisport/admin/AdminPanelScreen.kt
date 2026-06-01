package io.untungs.nutrisport.admin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.untungs.nutrisport.core.ui.component.PrimaryTopAppBar
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.icons.Plus
import io.untungs.nutrisport.core.ui.icons.Search
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme

@Composable
fun AdminPanelRoute(
    navigateBack: () -> Unit,
) {
    AdminPanelScreen(navigateBack)
}

@Composable
private fun AdminPanelScreen(
    navigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            PrimaryTopAppBar(
                title = "Admin Panel",
                onBackClick = navigateBack,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Search, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Plus,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}

@Preview
@Composable
private fun AdminPanelScreenPreview() {
    NutriSportTheme {
        AdminPanelScreen {}
    }
}
