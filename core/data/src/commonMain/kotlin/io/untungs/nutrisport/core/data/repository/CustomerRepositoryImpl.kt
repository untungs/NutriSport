package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import io.untungs.nutrisport.core.data.model.RoleDto
import io.untungs.nutrisport.core.data.util.toDomainException
import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

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

    override suspend fun saveRole(id: String, isAdmin: Boolean) {
        try {
            Firebase.firestore.collection(CUSTOMER_COLLECTION)
                .document(id)
                .collection(PRIVATE_DATA_COLLECTION)
                .document(ROLE_DOCUMENT)
                .set(RoleDto(isAdmin = isAdmin))
        } catch (e: Exception) {
            throw e.toDomainException()
        }
    }

    override fun getCustomerFlow(): Flow<Customer?> {
        val userId = Firebase.auth.currentUser?.uid
            ?: return flowOf(null)

        val customerSnapshots = Firebase.firestore.collection(CUSTOMER_COLLECTION)
            .document(userId)
            .snapshots

        val roleSnapshots = Firebase.firestore.collection(CUSTOMER_COLLECTION)
            .document(userId)
            .collection(PRIVATE_DATA_COLLECTION)
            .document(ROLE_DOCUMENT)
            .snapshots

        return combine(customerSnapshots, roleSnapshots) { customerSnap, roleSnap ->
            val customer = if (customerSnap.exists) customerSnap.data<Customer>() else null
            val isAdmin = if (roleSnap.exists) roleSnap.data<RoleDto>().isAdmin else false
            customer?.copy(isAdmin = isAdmin)
        }.catch { e -> throw e.toDomainException() }
    }

    companion object {
        private const val CUSTOMER_COLLECTION = "customer"
        private const val PRIVATE_DATA_COLLECTION = "privateData"
        private const val ROLE_DOCUMENT = "role"
    }
}
