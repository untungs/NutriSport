package io.untungs.nutrisport.core.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import io.untungs.nutrisport.core.data.util.toDomainException
import io.untungs.nutrisport.core.domain.repository.ImageRepository
import kotlin.time.Clock

class ImageRepositoryImpl(
    private val supabaseClient: SupabaseClient
) : ImageRepository {

    override suspend fun uploadProductImage(productId: String, bytes: ByteArray): String {
        return try {
            val bucket = supabaseClient.storage.from(PRODUCT_BUCKET)
            val fileName = "${productId}_${Clock.System.now().toEpochMilliseconds()}.jpg"

            bucket.upload(fileName, bytes) {
                upsert = true
            }

            bucket.publicUrl(fileName)
        } catch (e: Exception) {
            throw e.toDomainException()
        }
    }

    override suspend fun deleteProductImage(url: String) {
        try {
            val bucket = supabaseClient.storage.from(PRODUCT_BUCKET)
            val fileName = url.substringAfterLast("/")
            bucket.delete(fileName)
        } catch (e: Exception) {
            throw e.toDomainException()
        }
    }

    companion object {
        private const val PRODUCT_BUCKET = "products"
    }
}
