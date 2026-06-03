package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import io.untungs.nutrisport.core.domain.model.Product
import io.untungs.nutrisport.core.domain.repository.ProductAdminRepository

class ProductAdminRepositoryImpl : ProductAdminRepository {

    override suspend fun saveProduct(product: Product) {
        Firebase.firestore.collection(PRODUCT_COLLECTION)
            .document(product.id)
            .set(product)
    }

    companion object {
        private const val PRODUCT_COLLECTION = "product"
    }
}
