package com.infosupport.pitstop.vehicles

import io.quarkus.panache.common.Page
import java.net.URI
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

@Path("/vehicles")
class VehiclesResource(private val eventPublisher: EventPublisher) {
    @GET
    @Path("{licenseNumber}")
    @Produces(APPLICATION_JSON)
    fun details(@PathParam("licenseNumber") licenseNumber: String): Response =
        when (val vehicle = Vehicle.findByLicenseNumber(licenseNumber)) {
            null -> Response.status(4040).build()
            else -> Response.ok(vehicle).build()
        }

    @GET
    @Produces(APPLICATION_JSON)
    fun list(@QueryParam("page")page: Int) = PagedResult(
        Vehicle.findAll().page(Page(page, 20)).list(),
        page, 20, Vehicle.count())

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Transactional
    fun create(@Valid cmd: RegisterVehicleCommand): Response {
        val vehicle = Vehicle(cmd.licenseNumber, cmd.brand, cmd.type, cmd.ownerId)
        vehicle.persist()

        eventPublisher.publishVehicleRegistered(
            VehicleRegistered(
                vehicle.id ?: 0,
                vehicle.licenseNumber,
                vehicle.brand,
                vehicle.type,
                vehicle.ownerId.toLong()
            )
        )

        return Response.created(URI.create("/vehicles/${vehicle.licenseNumber}")).build()
    }
}