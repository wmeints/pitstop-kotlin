package com.pitstop.customermanagement.application.queryhandlers

import com.pitstop.customermanagement.common.QueryHandler
import com.pitstop.customermanagement.domain.aggregates.customers.CustomerRepository
import com.pitstop.customermanagement.domain.aggregates.customers.queries.GetCustomerQuery
import com.pitstop.customermanagement.domain.aggregates.customers.queries.GetCustomerQueryResult
import org.springframework.stereotype.Component
import javax.persistence.EntityNotFoundException

@Component
class GetCustomerQueryHandler(
    private val customerRepository: CustomerRepository
) : QueryHandler<GetCustomerQuery, GetCustomerQueryResult> {

    override fun execute(request: GetCustomerQuery): GetCustomerQueryResult {
        val customer = customerRepository.findById(request.customerId)

        val result = customer.map {
            GetCustomerQueryResult(
                it.id,
                it.name,
                it.address,
                it.zipCode,
                it.city,
                it.telephoneNumber,
                it.emailAddress
            )
        }

        return result.orElseThrow { EntityNotFoundException() }
    }
}