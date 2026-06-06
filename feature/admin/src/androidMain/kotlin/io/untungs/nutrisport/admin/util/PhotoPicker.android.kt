package io.untungs.nutrisport.admin.util

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PhotoPicker {

    private var isOpen by mutableStateOf(false)

    @Composable
    actual fun InitializePhotoPicker(onSelectImage: (ByteArray?) -> Unit) {
        val context = LocalContext.current
        val pickerLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia()
        ) { uri ->
            isOpen = false
            val bytes = uri?.let {
                context.contentResolver.openInputStream(it)?.use { inputStream ->
                    inputStream.readBytes()
                }
            }
            onSelectImage(bytes)
        }

        LaunchedEffect(isOpen) {
            if (isOpen) {
                pickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    actual fun open() {
        isOpen = true
    }
}
