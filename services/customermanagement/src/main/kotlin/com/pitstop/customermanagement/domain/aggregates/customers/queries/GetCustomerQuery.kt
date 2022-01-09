package com.pitstop.customermanagement.domain.aggregates.customers.queries

import com.pitstop.customermanagement.common.Request
import java.util.*

data class GetCustomerQuery(val customerId: UUID) : Request<GetCustomerQueryResult>

