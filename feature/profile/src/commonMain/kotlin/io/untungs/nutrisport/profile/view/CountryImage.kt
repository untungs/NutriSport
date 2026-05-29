package io.untungs.nutrisport.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.domain.model.Country
import io.untungs.nutrisport.profile.util.flagIcon

@Composable
fun CountryImage(
    country: Country,
    modifier: Modifier = Modifier,
    colorFilter: ColorFilter? = null,
) {
    Image(
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = CircleShape,
            ),
        imageVector = country.flagIcon,
        contentDescription = "${country.countryName} flag icon",
        colorFilter = colorFilter
    )
}