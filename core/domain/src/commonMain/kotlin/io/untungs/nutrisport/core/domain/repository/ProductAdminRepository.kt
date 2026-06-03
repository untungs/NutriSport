package io.untungs.nutrisport.core.domain.repository

import io.untungs.nutrisport.core.domain.model.Product

interface ProductAdminRepository {

    suspend fun saveProduct(product: Product)
}