package io.untungs.nutrisport.core.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.ui.Alpha
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onClick: (() -> Unit)? = null
) {
    val borderColor by animateColorAsState(
        targetValue = if (isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.outline
    )

    val isClickable = onClick != null

    Box(
        modifier = modifier.then(
            if (isClickable) {
                Modifier.semantics { role = Role.Button }
            } else Modifier
        ),
        propagateMinConstraints = true
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(6.dp))
                .then(
                    if (isClickable) {
                        Modifier
                            .focusProperties { canFocus = false }
                            .clearAndSetSemantics { }
                    } else Modifier
                ),
            enabled = enabled,
            readOnly = isClickable,
            leadingIcon = leadingIcon,
            placeholder = {
                placeholder?.let {
                    Text(modifier = Modifier.alpha(Alpha.HALF), text = it)
                }
            },
            singleLine = singleLine,
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceBright,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceDim,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = if (isClickable) Color.Transparent else MaterialTheme.colorScheme.secondary,
                selectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.secondary,
                    backgroundColor = MaterialTheme.colorScheme.secondary.copy(alpha = Alpha.HALF)
                ),
            )
        )

        if (onClick != null && enabled) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(6.dp))
                    .clickable(onClick = onClick)
            )
        }
    }
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    NutriSportTheme {
        CustomTextField(
            "",
            {},
            placeholder = "Placeholder"
        )
    }
}