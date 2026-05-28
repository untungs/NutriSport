package io.untungs.nutrisport.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.ui.Images
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    image: DrawableResource? = Images.Cat,
    title: String? = null,
    subtitle: String,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (image != null) {
            Image(
                modifier = Modifier
                    .padding(24.dp)
                    .size(60.dp),
                painter = painterResource(image),
                contentDescription = "Info card image"
            )
        }
        if (title != null) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = title,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun InfoCardPreview() {
    NutriSportTheme {
        Surface {
            InfoCard(
                title = "Oops!",
                subtitle = "Something went wrong"
            )
        }
    }
}