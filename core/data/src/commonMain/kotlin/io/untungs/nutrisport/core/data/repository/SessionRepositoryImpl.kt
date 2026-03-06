package io.untungs.nutrisport.core.data.repository

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import io.untungs.nutrisport.core.domain.model.AuthState
import io.untungs.nutrisport.core.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionRepositoryImpl : SessionRepository {

    override val authState: Flow<AuthState>
        get() = Firebase.auth.authStateChanged.map { firebaseUser ->
            if (firebaseUser != null) {
                AuthState.Authenticated(firebaseUser.uid)
            } else {
                AuthState.Unauthenticated
            }
        }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }
}
