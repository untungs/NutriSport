package io.untungs.nutrisport.admin.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.model.ProductCategory
import io.untungs.nutrisport.core.ui.component.CustomProgressIndicator
import io.untungs.nutrisport.core.ui.component.CustomTextField
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.icons.Plus
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
data class ManageProductFormState(
    val id: String = Uuid.random().toHexString(),
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    // user inputs
    val title: String = "",
    val description: String = "",
    val thumbnail: String = "",
    val category: ProductCategory = ProductCategory.Protein,
    val flavors: String? = null,
    val weight: Int? = null,
    val price: Double = 0.0,
    val isNew: Boolean = false,
    val isPopular: Boolean = false,
    val isDiscounted: Boolean = false,
    // helper states
    val isImageUploading: Boolean = false,
    val isTitleTouched: Boolean = false,
    val isDescriptionTouched: Boolean = false,
    val isPriceTouched: Boolean = false
) {
    fun toProduct(updatedAt: Long) = Product(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        title = title,
        description = description,
        thumbnail = thumbnail,
        category = category.title,
        flavors = flavors?.split(",")?.map { it.trim() },
        weight = weight,
        price = price,
        isNew = isNew,
        isPopular = isPopular,
        isDiscounted = isDiscounted,
    )

    val isTitleValid: Boolean get() = title.length in 3..100
    val isDescriptionValid: Boolean get() = description.length in 10..1000
    val isPriceValid: Boolean get() = price > 0.0

    val isFormValid: Boolean
        get() = isTitleValid &&
                isDescriptionValid &&
                isPriceValid

    companion object {
        fun fromProduct(product: Product) = ManageProductFormState(
            id = product.id,
            createdAt = product.createdAt,
            title = product.title,
            description = product.description,
            thumbnail = product.thumbnail,
            category = ProductCategory.entries.find { it.title == product.category } ?: ProductCategory.Protein,
            flavors = product.flavors?.joinToString(", "),
            weight = product.weight,
            price = product.price,
            isNew = product.isNew,
            isPopular = product.isPopular,
            isDiscounted = product.isDiscounted,
            isTitleTouched = true,
            isDescriptionTouched = true,
            isPriceTouched = true
        )
    }
}

interface ManageProductFormAction {
    fun onTitleChange(value: String)
    fun onDescriptionChange(value: String)
    fun onThumbnailChange(value: String)
    fun onImageSelected(bytes: ByteArray)
    fun onCategoryChange(value: ProductCategory)
    fun onFlavorsChange(value: String)
    fun onWeightChange(value: Int?)
    fun onPriceChange(value: Double)
    fun onIsNewChange(value: Boolean)
    fun onIsPopularChange(value: Boolean)
    fun onIsDiscountedChange(value: Boolean)
}

@Composable
fun ManageProductForm(
    state: ManageProductFormState,
    action: ManageProductFormAction,
    onImageClick: () -> Unit,
) {
    var showCategoryPicker by remember { mutableStateOf(false) }

    if (showCategoryPicker) {
        CategoryPickerDialog(
            category = state.category,
            onConfirmClick = {
                action.onCategoryChange(it)
                showCategoryPicker = false
            },
            onDismiss = { showCategoryPicker = false }
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MainFields(
            state = state,
            action = action,
            onImageClick = onImageClick
        )
        CategoryFields(
            state = state,
            onCategoryClick = { showCategoryPicker = true },
            action = action
        )
        CustomTextField(
            value = if (state.price == 0.0) "" else state.price.toString(),
            onValueChange = { action.onPriceChange(it.toDoubleOrNull() ?: 0.0) },
            placeholder = "Price",
            isError = !state.isPriceValid && state.isPriceTouched,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            )
        )
        SwitchRow("New", state.isNew, action::onIsNewChange)
        SwitchRow("Popular", state.isPopular, action::onIsPopularChange)
        SwitchRow("Discounted", state.isDiscounted, action::onIsDiscountedChange)
    }
}

@Composable
private fun MainFields(
    state: ManageProductFormState,
    action: ManageProductFormAction,
    onImageClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colorScheme.surfaceBright)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(enabled = !state.isImageUploading, onClick = onImageClick),
        contentAlignment = Alignment.Center
    ) {
        if (state.isImageUploading) {
            CustomProgressIndicator()
        } else if (state.thumbnail.isNotEmpty()) {
            AsyncImage(
                model = state.thumbnail,
                contentDescription = "Product image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Plus,
                contentDescription = "Add product image"
            )
        }
    }
    CustomTextField(
        value = state.title,
        onValueChange = action::onTitleChange,
        placeholder = "Title",
        isError = !state.isTitleValid && state.isTitleTouched,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
    )
    CustomTextField(
        modifier = Modifier.height(120.dp),
        value = state.description,
        onValueChange = action::onDescriptionChange,
        placeholder = "Description",
        isError = !state.isDescriptionValid && state.isDescriptionTouched,
        singleLine = false,
    )
}

@Composable
private fun CategoryFields(
    state: ManageProductFormState,
    onCategoryClick: () -> Unit,
    action: ManageProductFormAction
) {
    CustomTextField(
        value = state.category.title,
        onValueChange = {},
        placeholder = "Category",
        onClick = onCategoryClick,
    )

    if (state.category.isConsumable) {
        CustomTextField(
            value = state.weight?.toString().orEmpty(),
            onValueChange = { action.onWeightChange(it.toIntOrNull()) },
            placeholder = "Weight",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        CustomTextField(
            value = state.flavors ?: "",
            onValueChange = action::onFlavorsChange,
            placeholder = "Flavors",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )
    }
}

@Composable
private fun SwitchRow(title: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = isChecked,
                onValueChange = onCheckedChange,
                role = Role.Switch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        Switch(
            checked = isChecked,
            onCheckedChange = null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.surfaceBright,
                checkedTrackColor = MaterialTheme.colorScheme.secondary,
                uncheckedThumbColor = MaterialTheme.colorScheme.surface,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent,
            )
        )
    }
}
