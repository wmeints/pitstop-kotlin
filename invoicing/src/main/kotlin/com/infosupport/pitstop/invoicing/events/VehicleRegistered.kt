package com.infosupport.pitstop.invoicing.events

data class VehicleRegistered(
    val vehicleId: Long,
    val licenseNumber: String,
    val brand: String,
    val type: String,
    val ownerId: Long
)