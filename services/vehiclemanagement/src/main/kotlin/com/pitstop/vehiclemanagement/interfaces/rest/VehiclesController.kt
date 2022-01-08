package com.pitstop.vehiclemanagement.interfaces.rest

import com.pitstop.vehiclemanagement.application.commandhandlers.RegisterVehicleCommandHandler
import com.pitstop.vehiclemanagement.application.queryhandlers.GetAllVehiclesQueryHandler
import com.pitstop.vehiclemanagement.application.queryhandlers.GetVehicleQueryHandler
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.commands.RegisterVehicleCommand
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries.GetAllVehiclesQuery
import com.pitstop.vehiclemanagement.domain.aggregates.vehicles.queries.GetVehicleQuery
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/vehicles")
class VehiclesController(
    private val getVehicleQueryHandler: GetVehicleQueryHandler,
    private val getAllVehiclesQueryHandler: GetAllVehiclesQueryHandler,
    private val registerVehicleCommandHandler: RegisterVehicleCommandHandler
) {
    @GetMapping
    fun getAllVehicles(): ResponseEntity<*> {
        val result = getAllVehiclesQueryHandler.execute(GetAllVehiclesQuery())
        return ResponseEntity.ok(result)
    }

    @GetMapping("{licenseNumber}")
    fun getVehicleByLicenseNumber(@PathVariable licenseNumber: String): ResponseEntity<*> {
        val result = getVehicleQueryHandler.execute(GetVehicleQuery(licenseNumber))
        return ResponseEntity.ok(result)
    }

    @PostMapping
    fun registerVehicle(@RequestBody cmd: RegisterVehicleCommand) : ResponseEntity<*> {
        val result = registerVehicleCommandHandler.execute(cmd)
        return ResponseEntity.ok(result)
    }
}