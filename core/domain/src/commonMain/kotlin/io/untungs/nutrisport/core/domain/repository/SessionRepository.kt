package io.untungs.nutrisport.core.domain.repository

import io.untungs.nutrisport.core.domain.model.AuthState
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    val authState: Flow<AuthState>
    suspend fun signOut()
}
