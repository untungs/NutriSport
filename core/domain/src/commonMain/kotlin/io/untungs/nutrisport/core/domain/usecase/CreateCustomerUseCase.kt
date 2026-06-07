package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository

class CreateCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(id: String, displayName: String, email: String): Result<Unit> {
        return runCatching {
            val isCustomerExist = customerRepository.isCustomerExist(id)
            if (!isCustomerExist) {
                val customer = Customer(id, displayName, email)
                customerRepository.saveCustomer(customer)
                customerRepository.saveRole(id, false)
            }
        }
    }
}
