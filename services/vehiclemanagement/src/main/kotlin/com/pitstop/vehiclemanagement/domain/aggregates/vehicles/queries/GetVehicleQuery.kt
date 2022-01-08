package com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries

import com.pitstop.vehiclemanagement.common.Request

data class GetVehicleQuery(val licenseNumber: String): Request<GetVehicleQueryResult>
