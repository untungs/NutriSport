package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
    operator fun invoke(): Flow<Result<Customer>> {
        return customerRepository.getCustomerFlow()
    }
}
