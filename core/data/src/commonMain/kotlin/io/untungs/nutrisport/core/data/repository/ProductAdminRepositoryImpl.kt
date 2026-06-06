package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductAdminRepositoryImpl : ProductAdminRepository {

    override suspend fun saveProduct(product: Product) {
        Firebase.firestore.collection(PRODUCT_COLLECTION)
            .document(product.id)
            .set(product)
    }

    override fun getProducts(): Flow<List<Product>> {
        return Firebase.firestore.collection(PRODUCT_COLLECTION)
            .snapshots()
            .map { it.documents.map { snapshot -> snapshot.data() } }
    }

    override fun getProduct(productId: String): Flow<Product?> {
        return Firebase.firestore.collection(PRODUCT_COLLECTION)
            .document(productId)
            .snapshots()
            .map { it.data() }
    }

    companion object {
        private const val PRODUCT_COLLECTION = "product"
    }
}
