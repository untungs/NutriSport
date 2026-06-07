package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import io.untungs.nutrisport.core.data.util.toDomainException
import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CustomerRepositoryImpl : CustomerRepository {

    override suspend fun isCustomerExist(id: String): Boolean {
        return try {
            Firebase.firestore.collection(CUSTOMER_COLLECTION).document(id).get().exists
        } catch (e: Exception) {
            throw e.toDomainException()
        }
    }

    override suspend fun saveCustomer(customer: Customer) {
        try {
            Firebase.firestore.collection(CUSTOMER_COLLECTION).document(customer.id).set(customer)
        } catch (e: Exception) {
            throw e.toDomainException()
        }
    }

    override fun getCustomerFlow(): Flow<Customer?> {
        val userId = Firebase.auth.currentUser?.uid
            ?: return flowOf(null)

        return Firebase.firestore.collection(CUSTOMER_COLLECTION)
            .document(userId)
            .snapshots
            .map { it.data<Customer>() }
            .catch { e -> throw e.toDomainException() }
    }

    companion object {
        private const val CUSTOMER_COLLECTION = "customer"
    }
}