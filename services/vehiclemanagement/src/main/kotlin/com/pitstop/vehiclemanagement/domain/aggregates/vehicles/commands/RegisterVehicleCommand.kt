package com.pitstop.vehiclemanagement.domain.aggregates.vehicles.commands

import com.pitstop.vehiclemanagement.common.Request
import com.pitstop.vehiclemanagement.common.Response

data class RegisterVehicleCommand(
    val licenseNumber: String,
    val brand: String,
    val type: String,
    val ownerId: String
) : Request<RegisterVehicleCommandResult>

data class RegisterVehicleCommandResult(val licenseNumber: String) : Response