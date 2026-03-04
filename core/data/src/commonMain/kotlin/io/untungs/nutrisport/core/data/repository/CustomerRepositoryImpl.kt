package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository

class CustomerRepositoryImpl : CustomerRepository {

    override suspend fun isCustomerExist(id: String): Boolean {
        return Firebase.firestore.collection(CUSTOMER_COLLECTION).document(id).get().exists
    }

    override suspend fun createCustomer(customer: Customer) {
        Firebase.firestore.collection(CUSTOMER_COLLECTION).document(customer.id).set(customer)
    }

    companion object {
        private const val CUSTOMER_COLLECTION = "customer"
    }
}