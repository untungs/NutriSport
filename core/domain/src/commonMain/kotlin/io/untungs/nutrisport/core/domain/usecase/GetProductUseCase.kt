package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository
import kotlinx.coroutines.flow.Flow

class GetProductUseCase(
    private val productAdminRepository: ProductAdminRepository
) {
    operator fun invoke(productId: String): Flow<Product?> {
        return productAdminRepository.getProduct(productId)
    }
}
