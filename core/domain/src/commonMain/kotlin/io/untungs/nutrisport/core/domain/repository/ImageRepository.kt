package io.untungs.nutrisport.core.domain.repository

interface ImageRepository {
    /**
     * Uploads an image to the storage and returns the URL.
     */
    suspend fun uploadProductImage(productId: String, bytes: ByteArray): String
    
    suspend fun deleteProductImage(url: String)
}
