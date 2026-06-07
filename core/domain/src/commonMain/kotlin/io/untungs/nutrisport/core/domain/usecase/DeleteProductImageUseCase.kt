package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.repository.ImageRepository

class DeleteProductImageUseCase(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(url: String): Result<Unit> {
        return runCatching {
            if (url.isNotBlank()) {
                repository.deleteProductImage(url)
            }
        }
    }
}
