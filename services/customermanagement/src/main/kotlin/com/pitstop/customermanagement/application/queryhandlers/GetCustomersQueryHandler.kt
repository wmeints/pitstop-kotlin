package com.pitstop.customermanagement.application.queryhandlers

import com.pitstop.customermanagement.common.QueryHandler
import com.pitstop.customermanagement.domain.aggregates.customers.CustomerRepository
import com.pitstop.customermanagement.domain.aggregates.customers.queries.GetCustomersQuery
import com.pitstop.customermanagement.domain.aggregates.customers.queries.GetCustomersQueryResult
import org.springframework.stereotype.Component

@Component
class GetCustomersQueryHandler(
    private val customerRepository: CustomerRepository
): QueryHandler<GetCustomersQuery, GetCustomersQueryResult> {
    override fun execute(request: GetCustomersQuery): GetCustomersQueryResult {
        return GetCustomersQueryResult(customerRepository.findAll().toList())
    }
}