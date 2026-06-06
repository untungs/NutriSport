package io.untungs.nutrisport.core.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import io.untungs.nutrisport.core.ui.icons.BackArrow
import io.untungs.nutrisport.core.ui.icons.Icons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTopAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.BackArrow,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = actions
    )
}
