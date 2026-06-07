package io.untungs.nutrisport.admin

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.untungs.nutrisport.admin.view.ProductCard
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.util.DataState
import io.untungs.nutrisport.core.ui.component.CustomProgressIndicator
import io.untungs.nutrisport.core.ui.component.InfoCard
import io.untungs.nutrisport.core.ui.component.PrimaryTopAppBar
import io.untungs.nutrisport.core.ui.icons.Close
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
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchBarVisible by viewModel.searchBarVisible.collectAsStateWithLifecycle()

    AdminPanelScreen(
        state = state,
        searchQuery = searchQuery,
        searchBarVisible = searchBarVisible,
        onSearchQueryChange = viewModel::updateSearchQuery,
        onSearchBarVisibleChange = viewModel::updateSearchBarVisible,
        navigateBack = navigateBack,
        navigateToManageProduct = navigateToManageProduct
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AdminPanelScreen(
    state: DataState<List<Product>>,
    searchQuery: String,
    searchBarVisible: Boolean,
    onSearchQueryChange: (String) -> Unit,
    onSearchBarVisibleChange: (Boolean) -> Unit,
    navigateBack: () -> Unit,
    navigateToManageProduct: (String?) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Scaffold(
        topBar = {
            TopBar(
                searchBarVisible,
                focusRequester,
                searchQuery,
                onSearchQueryChange,
                onSearchBarVisibleChange,
                navigateBack
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
        LaunchedEffect(searchBarVisible) {
            if (searchBarVisible) {
                focusRequester.requestFocus()
            }
        }

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
                            title = if (searchQuery.isNotEmpty()) "No Results" else "No Products",
                            subtitle = if (searchQuery.isNotEmpty())
                                "No products match \"$searchQuery\"."
                            else "Click the + button to add your first product."
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(
                                start = 12.dp, top = 12.dp, end = 12.dp, bottom = 80.dp
                            ),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(products, key = { it.id }) { product ->
                                ProductCard(
                                    modifier = Modifier.animateItem(),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    searchBarVisible: Boolean,
    focusRequester: FocusRequester,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearchBarVisibleChange: (Boolean) -> Unit,
    navigateBack: () -> Unit
) {
    AnimatedContent(targetState = searchBarVisible) { visible ->
        if (visible) {
            SearchBar(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = searchQuery,
                        onQueryChange = onSearchQueryChange,
                        onSearch = {},
                        expanded = false,
                        onExpandedChange = {},
                        placeholder = { Text("Search here") },
                        trailingIcon = {
                            IconButton(onClick = {
                                if (searchQuery.isNotEmpty()) onSearchQueryChange("")
                                else onSearchBarVisibleChange(false)
                            }) {
                                Icon(Icons.Close, contentDescription = "Close")
                            }
                        }
                    )
                },
                expanded = false,
                onExpandedChange = {},
                content = {}
            )
        } else {
            PrimaryTopAppBar(
                title = "Admin Panel",
                onBackClick = navigateBack,
                actions = {
                    IconButton(onClick = { onSearchBarVisibleChange(true) }) {
                        Icon(imageVector = Icons.Search, contentDescription = "Search")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun AdminPanelScreenPreview() {
    NutriSportTheme {
        AdminPanelScreen(
            state = DataState.Loading,
            searchQuery = "",
            searchBarVisible = false,
            onSearchQueryChange = {},
            onSearchBarVisibleChange = {},
            navigateBack = {},
            navigateToManageProduct = {}
        )
    }
}
