package com.pitstop.customermanagement.domain.aggregates.customers.commands

import com.pitstop.customermanagement.common.Response
import java.util.*

data class RegisterCustomerCommandResult(val customerId: UUID): Response
