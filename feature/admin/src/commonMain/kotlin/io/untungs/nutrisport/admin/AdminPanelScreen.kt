package io.untungs.nutrisport.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.admin.view.ProductCard
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.util.DataState
import io.untungs.nutrisport.core.ui.component.CustomProgressIndicator
import io.untungs.nutrisport.core.ui.component.InfoCard
import io.untungs.nutrisport.core.ui.component.PrimaryTopAppBar
import io.untungs.nutrisport.core.ui.icons.Icons
import io.untungs.nutrisport.core.ui.icons.Plus
import io.untungs.nutrisport.core.ui.icons.Search
import io.untungs.nutrisport.core.ui.theme.NutriSportTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AdminPanelRoute(
    viewModel: AdminPanelViewModel = koinViewModel(),
    navigateBack: () -> Unit,
    navigateToManageProduct: (String?) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AdminPanelScreen(
        state = state,
        navigateBack = navigateBack,
        navigateToManageProduct = navigateToManageProduct
    )
}

@Composable
private fun AdminPanelScreen(
    state: DataState<List<Product>>,
    navigateBack: () -> Unit,
    navigateToManageProduct: (String?) -> Unit,
) {
    Scaffold(
        topBar = {
            PrimaryTopAppBar(
                title = "Admin Panel",
                onBackClick = navigateBack,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Search, contentDescription = "Search")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToManageProduct(null) }) {
                Icon(
                    imageVector = Icons.Plus,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                is DataState.Loading -> {
                    CustomProgressIndicator()
                }

                is DataState.Error -> {
                    InfoCard(
                        modifier = Modifier.padding(24.dp),
                        title = "Oops!",
                        subtitle = state.exception.message ?: "Something went wrong"
                    )
                }

                is DataState.Success -> {
                    val products = state.data
                    if (products.isEmpty()) {
                        InfoCard(
                            modifier = Modifier.padding(24.dp),
                            title = "No Products",
                            subtitle = "Click the + button to add your first product."
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 12.dp, top = 12.dp, end = 12.dp, bottom = 80.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(products) { product ->
                                ProductCard(
                                    product = product,
                                    onClick = { navigateToManageProduct(product.id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AdminPanelScreenPreview() {
    NutriSportTheme {
        AdminPanelScreen(
            state = DataState.Loading,
            navigateBack = {},
            navigateToManageProduct = {}
        )
    }
}
