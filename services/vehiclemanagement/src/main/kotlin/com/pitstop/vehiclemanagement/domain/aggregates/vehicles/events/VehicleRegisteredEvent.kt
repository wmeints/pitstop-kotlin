package com.pitstop.vehiclemanagement.domain.aggregates.vehicles.events

import com.pitstop.vehiclemanagement.common.DomainEvent

data class VehicleRegisteredEvent(val licenseNumber: String, val brand: String, val type: String, val ownerId: String): DomainEvent