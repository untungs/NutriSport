package io.untungs.nutrisport.admin.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.domain.model.ProductCategory
import io.untungs.nutrisport.core.ui.component.CustomAlertDialog
import io.untungs.nutrisport.core.ui.icons.Check
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme

@Composable
fun CategoryPickerDialog(
    category: ProductCategory,
    onConfirmClick: (ProductCategory) -> Unit,
    onDismiss: () -> Unit,
) {
    var selectedCategory by remember { mutableStateOf(category) }

    CustomAlertDialog(
        onDismissRequest = onDismiss,
        onConfirmClick = { onConfirmClick(selectedCategory) },
        title = "Select a Category",
        text = {
            CategoryPickerDialogContent(
                selectedCategory = selectedCategory,
                onSelect = { selectedCategory = it }
            )
        }
    )
}

@Composable
private fun CategoryPickerDialogContent(
    selectedCategory: ProductCategory,
    onSelect: (ProductCategory) -> Unit
) {
    val categories = remember { ProductCategory.entries }
    Column {
        LazyColumn {
            items(categories) { category ->
                CategoryItem(
                    category = category,
                    isSelected = category == selectedCategory,
                    onSelect = { onSelect(category) }
                )
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: ProductCategory,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        if (isSelected) Color(category.color).copy(alpha = 0.2f)
        else Color.Transparent
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .clickable(onClick = onSelect)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = category.title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        if (isSelected) {
            Icon(
                modifier = Modifier.size(18.dp),
                imageVector = Icons.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun CategoryPickerDialogContentPreview() {
    var category by remember { mutableStateOf(ProductCategory.Protein) }

    NutriSportTheme {
        Surface {
            CategoryPickerDialogContent(selectedCategory = category) {
                category = it
            }
        }
    }
}