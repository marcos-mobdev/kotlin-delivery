package br.com.appforge.kotlindelivery.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.appforge.kotlindelivery.data.remote.firebase.repository.IAuthenticationRepository
import br.com.appforge.kotlindelivery.domain.model.User
import br.com.appforge.kotlindelivery.domain.usecase.AuthenticationUseCase
import br.com.appforge.kotlindelivery.domain.usecase.ValidationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase,
    private val authenticationRepositoryImpl: IAuthenticationRepository
):ViewModel(){

    private val _validationResult = MutableLiveData<ValidationResult>()
    val validationResult : LiveData<ValidationResult> get() = _validationResult

    private val _success = MutableLiveData<Boolean>()
    val success : LiveData<Boolean> get() = _success

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    fun registerUser(user:User){
        //Check user data
        val validationReturn = authenticationUseCase.validateUserRegister(user)
        _validationResult.value = validationReturn
        //Register user
        if(validationReturn.validationRegisterSuccess ){
            _isLoading.value = true
            viewModelScope.launch {
                val result = authenticationRepositoryImpl.registerUser(user)
                _success.postValue(result)
                _isLoading.value = false
            }
        }
    }

    fun loginUser(user:User){
        //Check user data
        val validationReturn = authenticationUseCase.validateUserLogin(user)
        _validationResult.value = validationReturn
        //Login user
        if(validationReturn.validationLoginSuccess ){
            _isLoading.value = true
            viewModelScope.launch {
                val result = authenticationRepositoryImpl.loginUser(user)
                _success.postValue(result)
                _isLoading.value = false
            }
        }
    }

    fun checkUserLoggedIn():Boolean{
        return authenticationRepositoryImpl.isUserLoggedIn()
    }
}