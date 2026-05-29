package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import kotlinx.coroutines.withTimeout

class UpdateCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
    suspend operator fun invoke(customer: Customer): Result<Unit> {
        return runCatching {
            // Enforce a timeout so the UI doesn't hang indefinitely offline
            withTimeout(10000L) {
                customerRepository.saveCustomer(customer)
            }
        }
    }
}
