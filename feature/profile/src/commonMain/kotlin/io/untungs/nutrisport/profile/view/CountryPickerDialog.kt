package io.untungs.nutrisport.profile.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.core.ui.component.CustomTextField
import io.untungs.nutrisport.core.ui.component.InfoCard
import io.untungs.nutrisport.core.ui.icons.Check
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme

@Composable
fun CountryPickerDialog(
    country: Country,
    onConfirmClick: (Country) -> Unit,
    onDismiss: () -> Unit,
) {
    var selectedCountry by remember { mutableStateOf(country) }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.surface,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { onConfirmClick(selectedCountry) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    text = "Confirm",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
        title = {
            Text(
                text = "Select A Country",
                style = MaterialTheme.typography.titleLarge
            )
        },
        text = {
            CountryPickerDialogContent(
                selectedCountry = selectedCountry,
                onSelect = { selectedCountry = it }
            )
        },
    )
}

@Composable
private fun CountryPickerDialogContent(
    selectedCountry: Country,
    onSelect: (Country) -> Unit,
) {
    val allCountries = remember { Country.entries }
    var searchQuery by remember { mutableStateOf("") }
    val filteredCountries by remember {
        derivedStateOf {
            if (searchQuery.isBlank()) {
                allCountries
            } else allCountries.filter {
                it.countryName.contains(searchQuery, true) ||
                        it.dialCode.toString().contains(searchQuery)
            }
        }
    }

    Column {
        CustomTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = "Search country or dial code",
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (filteredCountries.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.height(300.dp)
            ) {
                items(filteredCountries, key = { it.code }) {
                    CountryPicker(
                        country = it,
                        isSelected = it == selectedCountry,
                        onSelect = { onSelect(it) }
                    )
                }
            }
        } else {
            InfoCard(
                modifier = Modifier.fillMaxWidth().height(100.dp),
                image = null,
                subtitle = "No results found for \"$searchQuery\""
            )
        }
    }
}

@Composable
private fun CountryPicker(
    modifier: Modifier = Modifier,
    country: Country,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val saturation by animateFloatAsState(
        if (isSelected) 1f else 0f
    )

    val colorMatrix = remember(saturation) {
        ColorMatrix().apply {
            setToSaturation(saturation)
        }
    }

    Row(
        modifier = modifier
            .clickable(
                onClickLabel = "Select ${country.countryName}",
                onClick = onSelect
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CountryImage(
            country = country,
            colorFilter = ColorFilter.colorMatrix(colorMatrix)
        )
        Text(
            modifier = Modifier.weight(1f)
                .padding(12.dp),
            text = "+${country.dialCode} (${country.countryName})",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Selector(isSelected = isSelected)
    }
}

@Composable
private fun Selector(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val animatedBackground by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surfaceBright
    )

    Box(
        modifier = modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(animatedBackground),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isSelected
        ) {
            Icon(
                modifier = Modifier.size(14.dp),
                imageVector = Icons.Check,
                contentDescription = "Checkmark icon",
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun CountryPickerDialogPreview() {
    var selectedCountry by remember { mutableStateOf(Country.INDONESIA) }

    NutriSportTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            CountryPickerDialogContent(
                selectedCountry = selectedCountry,
                onSelect = { selectedCountry = it }
            )
        }
    }
}
