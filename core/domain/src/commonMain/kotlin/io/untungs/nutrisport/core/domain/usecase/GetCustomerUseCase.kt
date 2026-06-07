package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.Customer
import io.untungs.nutrisport.core.domain.repository.CustomerRepository
import io.untungs.nutrisport.core.domain.util.DataState
import io.untungs.nutrisport.core.domain.util.asDataState
import kotlinx.coroutines.flow.Flow

class GetCustomerUseCase(
    private val customerRepository: CustomerRepository
) {
    operator fun invoke(): Flow<DataState<Customer?>> {
        return customerRepository.getCustomerFlow().asDataState()
    }
}
