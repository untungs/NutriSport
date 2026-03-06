package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.model.AuthState
import io.untungs.nutrisport.core.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow

class ObserveAuthStateUseCase(
    private val sessionRepository: SessionRepository
) {
    operator fun invoke(): Flow<AuthState> {
        return sessionRepository.authState
    }
}
