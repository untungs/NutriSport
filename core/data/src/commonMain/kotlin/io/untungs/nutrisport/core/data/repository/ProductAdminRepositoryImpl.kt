package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.Direction
import dev.gitlive.firebase.firestore.firestore
import io.untungs.nutrisport.core.data.util.toDomainException
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class ProductAdminRepositoryImpl : ProductAdminRepository {

    override suspend fun saveProduct(product: Product) {
        try {
            Firebase.firestore.collection(PRODUCT_COLLECTION)
                .document(product.id)
                .set(product)
        } catch (e: Exception) {
            throw e.toDomainException()
        }
    }

    override fun getProducts(): Flow<List<Product>> {
        return Firebase.firestore.collection(PRODUCT_COLLECTION)
            .orderBy("createdAt", Direction.DESCENDING)
            .snapshots()
            .map { it.documents.map { snapshot -> snapshot.data<Product>() } }
            .catch { throw it.toDomainException() }
    }

    override fun getProduct(productId: String): Flow<Product?> {
        return Firebase.firestore.collection(PRODUCT_COLLECTION)
            .document(productId)
            .snapshots()
            .map { it.data<Product>() }
            .catch { throw it.toDomainException() }
    }

    override suspend fun deleteProduct(productId: String) {
        try {
            Firebase.firestore.collection(PRODUCT_COLLECTION)
                .document(productId)
                .delete()
        } catch (e: Exception) {
            throw e.toDomainException()
        }
    }

    companion object {
        private const val PRODUCT_COLLECTION = "product"
    }
}
