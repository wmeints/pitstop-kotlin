package com.pitstop.customermanagement.domain.aggregates.customers.events

import com.pitstop.customermanagement.common.DomainEvent
import java.util.*

data class CustomerRegisteredEvent(
    val customerId: UUID,
    val name: String,
    val address: String,
    val zipCode: String,
    val city: String,
    val telephoneNumber: String,
    val emailAddress: String
): DomainEvent
