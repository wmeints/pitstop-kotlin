package com.infosupport.pitstop.customers

data class RegisterCustomerCommand(
    val name : String,
    val address : String,
    val zipCode : String,
    val city : String,
    val telephoneNumber : String,
    val emailAddress : String
)
