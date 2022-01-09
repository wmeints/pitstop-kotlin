package com.pitstop.customermanagement.domain.aggregates.customers.queries

import com.pitstop.customermanagement.common.Response
import java.util.*

data class GetCustomerQueryResult(
    val id: UUID,
    val name: String,
    val address: String,
    val zipCode: String,
    val city: String,
    val telephoneNumber: String,
    val emailAddress: String
) : Response