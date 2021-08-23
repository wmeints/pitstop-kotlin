package com.infosupport.pitstop.invoicing.eventlisteners

import com.infosupport.pitstop.invoicing.entities.Vehicle
import com.infosupport.pitstop.invoicing.events.VehicleRegistered
import com.infosupport.pitstop.invoicing.repositories.VehicleRepository
import io.vertx.core.json.JsonObject
import org.eclipse.microprofile.reactive.messaging.Incoming
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class VehicleRegisteredEventListener(private val vehicleRepository: VehicleRepository) {
    @Transactional
    @Incoming("vehicle-registered")
    fun consumeVehicleRegistered(payload: JsonObject) {
        val evt = payload.mapTo(VehicleRegistered::class.java)
        val vehicle = Vehicle(evt.vehicleId, evt.licenseNumber, evt.brand, evt.type, evt.ownerId)

        vehicleRepository.persist(vehicle)
    }
}