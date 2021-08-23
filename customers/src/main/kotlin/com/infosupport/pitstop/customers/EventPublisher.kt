package com.infosupport.pitstop.customers

import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EventPublisher(@Channel("customer-registered") private val customerRegisteredEmitter: Emitter<CustomerRegistered>) {
    fun publishCustomerRegistered(evt: CustomerRegistered) {
        customerRegisteredEmitter.send(evt)
    }
}