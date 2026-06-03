package io.untungs.nutrisport.core.domain.repository

import io.untungs.nutrisport.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductAdminRepository {

    suspend fun saveProduct(product: Product)

    fun getProducts(): Flow<List<Product>>

    fun getProduct(productId: String): Flow<Product?>
}