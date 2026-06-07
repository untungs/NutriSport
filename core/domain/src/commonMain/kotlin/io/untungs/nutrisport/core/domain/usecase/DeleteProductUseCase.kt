package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository

class DeleteProductUseCase(
    private val repository: ProductAdminRepository
) {
    suspend operator fun invoke(productId: String): Result<Unit> {
        return runCatching {
            repository.deleteProduct(productId)
        }
    }
}
