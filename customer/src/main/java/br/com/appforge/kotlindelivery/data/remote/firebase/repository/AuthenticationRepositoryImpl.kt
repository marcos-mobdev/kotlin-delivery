package br.com.appforge.kotlindelivery.data.remote.firebase.repository

import br.com.appforge.kotlindelivery.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl@Inject constructor(
    private val firebaseAuth: FirebaseAuth
):IAuthenticationRepository {
    override suspend fun registerUser(user: User): Boolean {
        return firebaseAuth
            .createUserWithEmailAndPassword(user.email, user.password)
            .await() != null
    }

    override suspend fun loginUser(user: User): Boolean {
        return firebaseAuth
            .signInWithEmailAndPassword(user.email, user.password)
            .await() != null
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }


}