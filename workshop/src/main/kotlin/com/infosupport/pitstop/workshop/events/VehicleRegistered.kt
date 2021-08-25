package com.infosupport.pitstop.workshop.events

data class VehicleRegistered(
    val vehicleId: Long,
    val licenseNumber: String,
    val brand: String,
    val type: String,
    val ownerId: Long
)