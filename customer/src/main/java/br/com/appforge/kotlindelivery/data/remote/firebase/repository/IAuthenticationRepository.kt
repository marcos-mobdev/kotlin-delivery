package br.com.appforge.kotlindelivery.data.remote.firebase.repository

import br.com.appforge.core.UIState
import br.com.appforge.kotlindelivery.domain.model.User

interface IAuthenticationRepository {
    suspend fun registerUser(user:User):Boolean
    suspend fun loginUser(user:User,uiStatus:(UIState<Boolean>)->Unit)
    fun isUserLoggedIn():Boolean
}