package com.pitstop.vehiclemanagement.domain.aggregates.vehicles

import com.pitstop.vehiclemanagement.common.DomainEvent
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.commands.RegisterVehicleCommand
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.events.VehicleRegisteredEvent
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.ConstraintViolationException
import javax.validation.Validation

@Entity
@Table(name = "vehicles")
class Vehicle(id: UUID, licenseNumber: String, brand: String, type: String, ownerId: String) {
    @Id
    var id = id
        protected set

    var licenseNumber = licenseNumber
        protected set

    var brand = brand
        protected set

    var type = type
        protected set

    var ownerId = ownerId
        protected set

    @Transient
    val eventsToPublish: MutableList<DomainEvent> = mutableListOf()

    companion object {
        /**
         * Registers a new vehicle
         */
        fun register(cmd: RegisterVehicleCommand): Vehicle {
            val validator = Validation.buildDefaultValidatorFactory().validator
            val violations = validator.validate(cmd)

            if (!violations.isEmpty()) {
                throw ConstraintViolationException(violations)
            }

            val vehicle = Vehicle(UUID.randomUUID(), cmd.licenseNumber, cmd.brand, cmd.type, cmd.ownerId)

            vehicle.eventsToPublish.add(
                VehicleRegisteredEvent(
                    vehicle.licenseNumber,
                    vehicle.brand,
                    vehicle.type,
                    vehicle.ownerId
                )
            )

            return vehicle
        }
    }
}