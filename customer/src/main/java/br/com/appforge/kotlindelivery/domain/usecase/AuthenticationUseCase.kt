package br.com.appforge.kotlindelivery.domain.usecase

import br.com.appforge.kotlindelivery.domain.model.User
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator

class AuthenticationUseCase {
    fun validateUserRegister(user:User) : ValidationResult{
        val nameValidation = user.name.validator()
            .minLength(6)
            .maxLength(50)
            .check()

        val emailValidation = user.email.validator()
            .validEmail()
            .check()

        val passwordValidation = user.password.validator()
            .minLength(6)
            .atleastOneUpperCase()
            .atleastOneLowerCase()
            .atleastOneNumber()
            .check()

        val phoneValidation = user.phone.validator()
            .minLength(14)
            .check()

        return ValidationResult(
            name = nameValidation,
            email = emailValidation,
            password = passwordValidation,
            phone = phoneValidation
        )
    }

    fun validateUserLogin(user:User) : ValidationResult{

        val emailValidation = user.email.validator()
            .validEmail()
            .check()

        val passwordValidation = user.password.validator()
            .minLength(6)
            .atleastOneUpperCase()
            .atleastOneLowerCase()
            .atleastOneNumber()
            .check()

        return ValidationResult(
            email = emailValidation,
            password = passwordValidation,
        )
    }
}