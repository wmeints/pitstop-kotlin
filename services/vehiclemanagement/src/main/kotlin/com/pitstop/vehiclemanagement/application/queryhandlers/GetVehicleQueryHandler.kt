package com.pitstop.vehiclemanagement.application.queryhandlers

import com.pitstop.vehiclemanagement.common.QueryHandler
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.VehicleRepository
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries.GetVehicleQuery
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries.GetVehicleQueryResult
import org.springframework.stereotype.Component
import javax.persistence.EntityNotFoundException

@Component
class GetVehicleQueryHandler(private val vehicleRepository: VehicleRepository) :
    QueryHandler<GetVehicleQuery, GetVehicleQueryResult> {
    override fun execute(request: GetVehicleQuery): GetVehicleQueryResult {
        val vehicle = vehicleRepository.findByLicenseNumber(request.licenseNumber)

        if (vehicle.isEmpty) {
            throw EntityNotFoundException()
        }

        return vehicle.map {
            GetVehicleQueryResult(it.licenseNumber, it.brand, it.type, it.ownerId)
        }.get()
    }
}