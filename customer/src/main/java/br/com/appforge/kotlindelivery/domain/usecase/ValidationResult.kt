package br.com.appforge.kotlindelivery.domain.usecase

data class ValidationResult(
    var name: Boolean = false,
    var email: Boolean = false,
    var password: Boolean = false,
    var phone: Boolean = false,

)
