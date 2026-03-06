package io.untungs.nutrisport.core.domain.usecase

import io.untungs.nutrisport.core.domain.repository.SessionRepository

class SignOutUseCase(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        sessionRepository.signOut()
    }
}
