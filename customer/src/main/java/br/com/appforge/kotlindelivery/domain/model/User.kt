package br.com.appforge.kotlindelivery.domain.model

data class User(
    val email:String,
    val password:String,
    val name:String = "",
    val phone:String = "",
)
