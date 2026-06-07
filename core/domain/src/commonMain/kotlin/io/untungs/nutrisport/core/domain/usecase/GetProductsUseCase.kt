package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository
import io.untungs.nutrisport.core.domain.util.DataState
import io.untungs.nutrisport.core.domain.util.asDataState
import kotlinx.coroutines.flow.Flow

class GetProductsUseCase(
    private val productAdminRepository: ProductAdminRepository
) {
    operator fun invoke(): Flow<DataState<List<Product>>> {
        return productAdminRepository.getProducts().asDataState()
    }
}
