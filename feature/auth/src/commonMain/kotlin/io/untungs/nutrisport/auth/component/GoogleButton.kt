package io.untungs.nutrisport.auth.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.ui.Images
import io.untungs.nutrisport.core.ui.component.CustomProgressIndicator
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun GoogleButton(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    icon: DrawableResource = Images.GoogleLogo,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceBright,
    onClick: () -> Unit,
) {
    val buttonText = if (loading) secondaryText else primaryText

    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = !loading,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            disabledContainerColor = backgroundColor
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedContent(
                targetState = loading,
                label = "button_content_anim"
            ) { isLoading ->
                if (isLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp,
                    )
                } else {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "Google Logo",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF)
@Composable
private fun GoogleButtonPreview() {
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    NutriSportTheme {
        GoogleButton(
            loading = isLoading,
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            isLoading = true
            scope.launch {
                delay(2_000)
                isLoading = false
            }
        }
    }
}