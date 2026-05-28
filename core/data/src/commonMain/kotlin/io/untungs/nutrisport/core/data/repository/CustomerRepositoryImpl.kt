package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CustomerRepositoryImpl : CustomerRepository {

    override suspend fun isCustomerExist(id: String): Boolean {
        return Firebase.firestore.collection(CUSTOMER_COLLECTION).document(id).get().exists
    }

    override suspend fun saveCustomer(customer: Customer) {
        Firebase.firestore.collection(CUSTOMER_COLLECTION).document(customer.id).set(customer)
    }

    override fun getCustomerFlow(): Flow<Result<Customer>> {
        val userId = Firebase.auth.currentUser?.uid
            ?: return flowOf(Result.failure(RuntimeException("User ID not found")))

        return Firebase.firestore.collection(CUSTOMER_COLLECTION)
            .document(userId)
            .snapshots
            .map { snapshot -> runCatching { snapshot.data<Customer>() } }
            .catch { e -> emit(Result.failure(e)) }
    }

    companion object {
        private const val CUSTOMER_COLLECTION = "customer"
    }
}