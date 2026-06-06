package io.untungs.nutrisport.admin.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.uikit.LocalUIViewController
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.dataWithContentsOfURL
import platform.PhotosUI.PHPickerConfiguration
import platform.PhotosUI.PHPickerFilter
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.posix.memcpy
import platform.darwin.NSObject
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PhotoPicker {

    private var isOpen by mutableStateOf(false)

    @OptIn(ExperimentalForeignApi::class)
    @Composable
    actual fun InitializePhotoPicker(onSelectImage: (ByteArray?) -> Unit) {
        val viewController = LocalUIViewController.current
        val delegate = remember {
            object : NSObject(), PHPickerViewControllerDelegateProtocol {
                override fun picker(picker: PHPickerViewController, didFinishPicking: List<*>) {
                    picker.dismissViewControllerAnimated(true, null)
                    isOpen = false

                    val result = didFinishPicking.firstOrNull() as? PHPickerResult
                    if (result == null) {
                        onSelectImage(null)
                        return
                    }

                    result.itemProvider.loadFileRepresentationForTypeIdentifier("public.image") { url, _ ->
                        if (url != null) {
                            val data = NSData.dataWithContentsOfURL(url)
                            val bytes = data?.let { nsData ->
                                ByteArray(nsData.length.toInt()).apply {
                                    usePinned {
                                        memcpy(it.addressOf(0), nsData.bytes, nsData.length)
                                    }
                                }
                            }
                            dispatch_async(dispatch_get_main_queue()) {
                                onSelectImage(bytes)
                            }
                        } else {
                            dispatch_async(dispatch_get_main_queue()) {
                                onSelectImage(null)
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(isOpen) {
            if (isOpen) {
                val configuration = PHPickerConfiguration()
                configuration.filter = PHPickerFilter.imagesFilter
                configuration.selectionLimit = 1
                val picker = PHPickerViewController(configuration)
                picker.delegate = delegate
                viewController.presentViewController(picker, true, null)
            }
        }
    }

    actual fun open() {
        isOpen = true
    }
}
