package br.com.appforge.kotlindelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.appforge.kotlindelivery.domain.model.User
import br.com.appforge.kotlindelivery.domain.usecase.AuthenticationUseCase
import br.com.appforge.kotlindelivery.domain.usecase.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
):ViewModel(){

    private val _validationResult = MutableLiveData<ValidationResult>()
    val validationResult : LiveData<ValidationResult> get() = _validationResult



    fun registerUser(user:User){
        //Check user data
        val validationReturn = authenticationUseCase.validateUserRegister(user)
        _validationResult.value = validationReturn
        //Register user
    }

    fun loginUser(user:User){
        //Check user data
        val validationReturn = authenticationUseCase.validateUserLogin(user)
        _validationResult.value = validationReturn
        //Register user
    }


}