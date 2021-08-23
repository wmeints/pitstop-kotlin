package com.infosupport.pitstop.invoicing.events

data class CustomerRegistered(
    val customerId: Long,
    val name: String,
    val address: String,
    val zipCode: String,
    val city: String,
    val telephoneNumber: String,
    val emailAddress: String
)

