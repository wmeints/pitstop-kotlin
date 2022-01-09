package com.pitstop.customermanagement.domain.aggregates.customers

import com.pitstop.customermanagement.common.DomainEvent
import com.pitstop.customermanagement.domain.aggregates.customers.commands.RegisterCustomerCommand
import com.pitstop.customermanagement.domain.aggregates.customers.events.CustomerRegisteredEvent
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.ConstraintViolationException
import javax.validation.Validation

@Entity
@Table(name = "customers")
class Customer(
    id: UUID,
    name: String,
    address: String,
    zipCode: String,
    city: String,
    telephoneNumber: String,
    emailAddress: String
) {
    @Id
    var id = id
        protected set

    var name = name
        protected set

    var address = address
        protected set

    var zipCode = zipCode
        protected set

    var city = city
        protected set

    var telephoneNumber = telephoneNumber
        protected set

    var emailAddress = emailAddress
        protected set

    @Transient
    val eventsToPublish: MutableList<DomainEvent> = mutableListOf()

    companion object {
        fun register(cmd: RegisterCustomerCommand): Customer {
            val validator = Validation.buildDefaultValidatorFactory().validator
            val violations = validator.validate(cmd)

            if (violations.isNotEmpty()) {
                throw ConstraintViolationException(violations)
            }

            val customer = Customer(
                UUID.randomUUID(),
                cmd.name,
                cmd.address,
                cmd.zipCode,
                cmd.city,
                cmd.telephoneNumber,
                cmd.emailAddress
            )

            customer.eventsToPublish.add(
                CustomerRegisteredEvent(
                    customer.id,
                    customer.name,
                    customer.address,
                    customer.zipCode,
                    customer.city,
                    customer.telephoneNumber,
                    customer.emailAddress
                )
            )

            return customer
        }
    }
}