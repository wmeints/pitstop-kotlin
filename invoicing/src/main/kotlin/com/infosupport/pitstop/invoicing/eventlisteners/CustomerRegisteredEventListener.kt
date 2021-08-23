package com.infosupport.pitstop.invoicing.eventlisteners

import com.infosupport.pitstop.invoicing.entities.Customer
import com.infosupport.pitstop.invoicing.events.CustomerRegistered
import com.infosupport.pitstop.invoicing.repositories.CustomerRepository
import io.vertx.core.json.JsonObject
import org.eclipse.microprofile.reactive.messaging.Incoming
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class CustomerRegisteredEventListener(private val customerRepository: CustomerRepository) {
    @Transactional
    @Incoming("customer-registered")
    fun consumeCustomerRegistered(payload: JsonObject) {
        val evt = payload.mapTo(CustomerRegistered::class.java)
        val customer = Customer(
            evt.customerId,
            evt.name,
            evt.address,
            evt.zipCode,
            evt.city,
            evt.telephoneNumber,
            evt.emailAddress
        )

        customerRepository.persist(customer)
    }
}