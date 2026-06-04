package io.untungs.nutrisport.admin.util

import androidx.compose.runtime.Composable

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class PhotoPicker() {
    @Composable
    fun InitializePhotoPicker(onSelectImage: (ByteArray?) -> Unit)
    fun open()
}
