package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository
import kotlinx.coroutines.withTimeout

class SubmitProductUseCase(
    private val productAdminRepository: ProductAdminRepository
) {
    suspend operator fun invoke(product: Product): Result<Unit> {
        return runCatching {
            withTimeout(10000L) {
                productAdminRepository.saveProduct(product)
            }
        }
    }
}
