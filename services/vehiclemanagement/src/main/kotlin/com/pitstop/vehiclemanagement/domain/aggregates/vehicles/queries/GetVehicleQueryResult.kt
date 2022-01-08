package com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries

import com.pitstop.vehiclemanagement.common.Response

data class GetVehicleQueryResult(
    val licenseNumber: String,
    val brand: String,
    val type: String,
    val ownerId: String
) : Response