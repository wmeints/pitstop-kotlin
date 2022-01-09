package com.pitstop.customermanagement.interfaces.rest

import com.pitstop.customermanagement.application.commandhandlers.RegisterCustomerCommandHandler
import com.pitstop.customermanagement.application.queryhandlers.GetCustomerQueryHandler
import com.pitstop.customermanagement.application.queryhandlers.GetCustomersQueryHandler
import com.pitstop.customermanagement.domain.aggregates.customers.commands.RegisterCustomerCommand
import com.pitstop.customermanagement.domain.aggregates.customers.queries.GetCustomerQuery
import com.pitstop.customermanagement.domain.aggregates.customers.queries.GetCustomersQuery
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/customers")
class CustomersController(
    private val registerCustomerCommandHandler: RegisterCustomerCommandHandler,
    private val getCustomersQueryHandler: GetCustomersQueryHandler,
    private val getCustomerQueryHandler: GetCustomerQueryHandler
) {
    @PostMapping
    fun registerCustomer(@RequestBody cmd: RegisterCustomerCommand): ResponseEntity<*> {
        val result = registerCustomerCommandHandler.execute(cmd)
        return ResponseEntity.created(URI("/customers/${result.customerId}")).build<Unit>() as ResponseEntity<*>
    }

    @GetMapping("{id}")
    fun getCustomer(@PathVariable id: UUID): ResponseEntity<*> {
        val result = getCustomerQueryHandler.execute(GetCustomerQuery(id))
        return ResponseEntity.ok(result)
    }

    @GetMapping
    fun getCustomers(): ResponseEntity<*> {
        val result = getCustomersQueryHandler.execute(GetCustomersQuery())
        return ResponseEntity.ok(result)
    }
}