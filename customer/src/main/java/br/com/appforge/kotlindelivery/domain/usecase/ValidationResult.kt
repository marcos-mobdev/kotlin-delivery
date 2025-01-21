package br.com.appforge.kotlindelivery.domain.usecase

data class ValidationResult(
    var name: Boolean = false,
    var email: Boolean = false,
    var password: Boolean = false,
    var phone: Boolean = false,

){
    val validationRegisterSuccess: Boolean
        get() = name && email && password && phone

    val validationLoginSuccess: Boolean
        get() = email && password
}
