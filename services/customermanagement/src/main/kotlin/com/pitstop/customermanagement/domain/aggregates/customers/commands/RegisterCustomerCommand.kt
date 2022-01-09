package com.pitstop.customermanagement.domain.aggregates.customers.commands

import com.pitstop.customermanagement.common.Request

data class RegisterCustomerCommand(
    val name: String,
    val address: String,
    val zipCode: String,
    val city: String,
    val telephoneNumber: String,
    val emailAddress: String
): Request<RegisterCustomerCommandResult>
