package com.infosupport.pitstop.workshop.exceptions

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class VehicleNotFoundExceptionMapper : ExceptionMapper<VehicleNotFoundException> {
    override fun toResponse(error: VehicleNotFoundException?): Response = Response.status(400)
        .entity(
            ProblemDetails(
                "https://pitstop.infosupport.com/exceptions/missing-association",
                "Required association not found.",
                "The vehicle with ID ${error?.vehicleId} not found."
            )
        )
        .build()
}