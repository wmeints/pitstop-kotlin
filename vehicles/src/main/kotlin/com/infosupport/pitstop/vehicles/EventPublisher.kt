package com.infosupport.pitstop.vehicles

import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EventPublisher(@Channel("vehicle-registered") private val vehicleRegisteredEmitter: Emitter<VehicleRegistered>) {
    fun publishVehicleRegistered(evt: VehicleRegistered) {
        vehicleRegisteredEmitter.send(evt)
    }
}