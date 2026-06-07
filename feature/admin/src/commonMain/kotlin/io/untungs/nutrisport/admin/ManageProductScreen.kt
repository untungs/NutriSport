package io.untungs.nutrisport.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.admin.util.PhotoPicker
import io.untungs.nutrisport.admin.view.ManageProductForm
import io.untungs.nutrisport.admin.view.ManageProductFormAction
import io.untungs.nutrisport.core.domain.model.ProductCategory
import io.untungs.nutrisport.core.domain.util.DataState
import io.untungs.nutrisport.core.ui.component.CustomAlertDialog
import io.untungs.nutrisport.core.ui.component.CustomProgressIndicator
import io.untungs.nutrisport.core.ui.component.InfoCard
import io.untungs.nutrisport.core.ui.component.PrimaryButton
import io.untungs.nutrisport.core.ui.component.PrimaryTopAppBar
import io.untungs.nutrisport.core.ui.icons.Check
import io.untungs.nutrisport.core.ui.icons.Delete
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.icons.Plus
import io.untungs.nutrisport.core.ui.icons.VerticalMenu
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ManageProductRoute(
    viewModel: ManageProductViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {
    val photoPicker = koinInject<PhotoPicker>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    photoPicker.InitializePhotoPicker { file ->
        if (file != null) {
            viewModel.uploadImage(file)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                ManageProductEvent.NavigateBack -> navigateBack()
            }
        }
    }

    ManageProductScreen(
        state = state,
        action = viewModel,
        onBackClick = { viewModel.cancelEdit() },
        onImageClick = { photoPicker.open() },
        onSubmitClick = { viewModel.submitProduct() },
        onDeleteClick = { viewModel.toggleDeleteConfirmation(true) },
        onConfirmDelete = { viewModel.deleteProduct() },
        onDismissDelete = { viewModel.toggleDeleteConfirmation(false) }
    )
}

@Composable
private fun ManageProductScreen(
    state: ManageProductState,
    action: ManageProductFormAction,
    onBackClick: () -> Unit,
    onImageClick: () -> Unit,
    onSubmitClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onConfirmDelete: () -> Unit,
    onDismissDelete: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            PrimaryTopAppBar(
                title = if (state.isNewProduct) "New Product" else "Edit Product",
                onBackClick = onBackClick,
                actions = {
                    if (!state.isNewProduct) {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.VerticalMenu,
                                contentDescription = "More"
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Delete",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        modifier = Modifier.size(20.dp),
                                        imageVector = Icons.Delete,
                                        contentDescription = null
                                    )
                                },
                                onClick = {
                                    showMenu = false
                                    onDeleteClick()
                                }
                            )
                        }
                    }
                }
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (state.showDeleteConfirmation) {
                CustomAlertDialog(
                    onDismissRequest = onDismissDelete,
                    title = "Delete Product",
                    text = {
                        Text(
                            text = "Are you sure you want to delete this product? This action cannot be undone.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    confirmButtonText = "Delete",
                    onConfirmClick = onConfirmDelete,
                    onDismissClick = onDismissDelete
                )
            }

            when (val productState = state.product) {
                is DataState.Loading -> {
                    CustomProgressIndicator()
                }

                is DataState.Error -> {
                    InfoCard(
                        modifier = Modifier.padding(24.dp),
                        title = "Oops!",
                        subtitle = productState.exception.message ?: "Something went wrong"
                    )
                }

                is DataState.Success -> {
                    if (productState.data == null && !state.isNewProduct) {
                        InfoCard(
                            modifier = Modifier.padding(24.dp),
                            title = "Oops!",
                            subtitle = "Product not found"
                        )
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(horizontal = 24.dp, vertical = 12.dp)
                        ) {
                            ManageProductForm(
                                state = state.formState,
                                action = action,
                                onImageClick = onImageClick
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            PrimaryButton(
                                modifier = Modifier.fillMaxWidth()
                                    .padding(top = 24.dp),
                                text = if (state.isNewProduct) "Add New Product" else "Update",
                                icon = if (state.isNewProduct) Icons.Plus else Icons.Check,
                                isLoading = state.isSubmitting,
                                enabled = state.formState.isFormValid,
                                onClick = onSubmitClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ManageProductScreenPreview() {
    val action = object : ManageProductFormAction {
        override fun onTitleChange(value: String) {}
        override fun onDescriptionChange(value: String) {}
        override fun onDeleteImageClick() {}
        override fun onCategoryChange(value: ProductCategory) {}
        override fun onFlavorsChange(value: String) {}
        override fun onWeightChange(value: Int?) {}
        override fun onPriceChange(value: Double) {}
        override fun onIsPopularChange(value: Boolean) {}
        override fun onIsDiscountedChange(value: Boolean) {}
        override fun onIsNewChange(value: Boolean) {}
    }

    NutriSportTheme {
        ManageProductScreen(
            state = ManageProductState(),
            action = action,
            onBackClick = {},
            onImageClick = {},
            onSubmitClick = {},
            onDeleteClick = {},
            onConfirmDelete = {},
            onDismissDelete = {}
        )
    }
}
