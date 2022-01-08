package com.pitstop.vehiclemanagement.application.commandhandlers

import com.pitstop.vehiclemanagement.common.CommandHandler
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.Vehicle
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.VehicleRepository
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.commands.RegisterVehicleCommand
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.commands.RegisterVehicleCommandResult
import org.springframework.stereotype.Component

@Component
class RegisterVehicleCommandHandler(private val vehicleRepository: VehicleRepository) :
    CommandHandler<RegisterVehicleCommand, RegisterVehicleCommandResult> {
    override fun execute(request: RegisterVehicleCommand): RegisterVehicleCommandResult {
        val vehicle = Vehicle.register(request)
        vehicleRepository.save(vehicle)

        return RegisterVehicleCommandResult(vehicle.licenseNumber)
    }
}