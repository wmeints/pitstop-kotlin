package com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries

import com.pitstop.vehiclemanagement.common.Response
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.Vehicle

data class GetAllVehiclesQueryResult(val items: List<Vehicle>): Response