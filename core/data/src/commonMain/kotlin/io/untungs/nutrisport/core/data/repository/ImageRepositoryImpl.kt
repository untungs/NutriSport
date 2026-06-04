package io.untungs.nutrisport.core.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import io.untungs.nutrisport.core.domain.repository.ImageRepository
import kotlin.time.Clock

class ImageRepositoryImpl(
    private val supabaseClient: SupabaseClient
) : ImageRepository {

    override suspend fun uploadProductImage(productId: String, bytes: ByteArray): Result<String> {
        return try {
            val bucket = supabaseClient.storage.from(PRODUCT_BUCKET)
            val fileName = "${productId}_${Clock.System.now().toEpochMilliseconds()}.jpg"

            bucket.upload(fileName, bytes) {
                upsert = true
            }

            val url = bucket.publicUrl(fileName)
            Result.success(url)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        private val PRODUCT_BUCKET = "products"
    }
}
