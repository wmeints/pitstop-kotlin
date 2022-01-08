package com.pitstop.vehiclemanagement.domain.aggregates.vehicles

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface VehicleRepository : CrudRepository<Vehicle, UUID> {
    fun findByLicenseNumber(licenseNumber: String): Optional<Vehicle>
}