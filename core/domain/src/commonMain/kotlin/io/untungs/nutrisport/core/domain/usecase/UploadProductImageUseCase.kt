package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.repository.ImageRepository

class UploadProductImageUseCase(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(productId: String, bytes: ByteArray): Result<String> {
        return runCatching {
            repository.uploadProductImage(productId, bytes)
        }
    }
}
