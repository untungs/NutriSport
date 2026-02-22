package io.untungs.nutrisport.auth.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.shared.BorderIdle
import io.untungs.nutrisport.shared.FontSize
import io.untungs.nutrisport.shared.IconSecondary
import io.untungs.nutrisport.shared.Image
import io.untungs.nutrisport.shared.SurfaceLighter
import io.untungs.nutrisport.shared.TextPrimary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    icon: DrawableResource = Image.GoogleLogo,
    shape: Shape = RoundedCornerShape(size = 99.dp),
    backgroundColor: Color = SurfaceLighter,
    borderColor: Color = BorderIdle,
    progressIndicatorColor: Color = IconSecondary,
    onClick: () -> Unit,
) {
    val buttonText = if (loading) secondaryText else primaryText

    Surface(
        onClick = onClick,
        enabled = !loading,
        modifier = modifier
            .border(width = 1.dp, color = borderColor, shape = shape),
        shape = shape,
        color = backgroundColor,
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .animateContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AnimatedContent(
                targetState = loading,
                label = "button_content_anim"
            ) { isLoading ->
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp,
                        color = progressIndicatorColor
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
                color = TextPrimary,
                fontSize = FontSize.REGULAR,
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