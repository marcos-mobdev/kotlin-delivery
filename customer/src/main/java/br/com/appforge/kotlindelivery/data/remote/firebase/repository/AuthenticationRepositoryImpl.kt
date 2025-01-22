package br.com.appforge.kotlindelivery.data.remote.firebase.repository

import br.com.appforge.core.UIState
import br.com.appforge.kotlindelivery.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
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

    override suspend fun loginUser(
        user: User,
        uiStatus:(UIState<Boolean>)->Unit
    ) {
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(user.email, user.password).await() != null
            if(result){
                uiStatus.invoke(
                    UIState.Success(true)
                )
            }

        }catch (invalidUserError:FirebaseAuthInvalidUserException){
            invalidUserError.printStackTrace()
            uiStatus.invoke(
                UIState.Error("Invalid email")
            )
        }catch (invalidCredentialsError:FirebaseAuthInvalidCredentialsException){
            invalidCredentialsError.printStackTrace()
            uiStatus.invoke(
                UIState.Error("Invalid password")
            )
        }catch (defaultError:Exception){
            defaultError.printStackTrace()
            uiStatus.invoke(
                UIState.Error("Access Denied. Try again later.")
            )
        }
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }


}