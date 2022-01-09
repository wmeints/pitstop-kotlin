package com.pitstop.customermanagement.domain.aggregates.customers.queries

import com.pitstop.customermanagement.common.Response
import com.pitstop.customermanagement.domain.aggregates.customers.Customer

data class GetCustomersQueryResult(val items: List<Customer>): Response