package com.pitstop.customermanagement.domain.aggregates.customers

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface CustomerRepository: CrudRepository<Customer, UUID> {
}