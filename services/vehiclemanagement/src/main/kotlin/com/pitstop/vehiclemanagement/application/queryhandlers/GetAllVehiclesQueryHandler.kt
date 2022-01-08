package com.pitstop.vehiclemanagement.application.queryhandlers

import com.pitstop.vehiclemanagement.common.QueryHandler
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.VehicleRepository
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries.GetAllVehiclesQuery
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries.GetAllVehiclesQueryResult
import org.springframework.stereotype.Component

@Component
class GetAllVehiclesQueryHandler(private val vehicleRepository: VehicleRepository) :
    QueryHandler<GetAllVehiclesQuery, GetAllVehiclesQueryResult> {
    override fun execute(request: GetAllVehiclesQuery): GetAllVehiclesQueryResult {
        return GetAllVehiclesQueryResult(vehicleRepository.findAll().toList())
    }
}