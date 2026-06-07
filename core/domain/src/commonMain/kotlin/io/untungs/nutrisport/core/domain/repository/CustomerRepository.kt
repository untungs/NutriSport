package io.untungs.nutrisport.core.domain.repository

import io.untungs.nutrisport.core.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun isCustomerExist(id: String): Boolean
    suspend fun saveCustomer(customer: Customer)
    fun getCustomerFlow(): Flow<Customer?>
}
