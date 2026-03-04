package io.untungs.nutrisport.core.domain.repository

import io.untungs.nutrisport.core.domain.model.Customer

interface CustomerRepository {
    suspend fun isCustomerExist(id: String): Boolean
    suspend fun createCustomer(customer: Customer)
}