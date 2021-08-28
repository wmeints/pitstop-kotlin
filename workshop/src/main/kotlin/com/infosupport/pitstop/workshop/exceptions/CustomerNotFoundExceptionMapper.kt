package com.infosupport.pitstop.workshop.exceptions

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class CustomerNotFoundExceptionMapper : ExceptionMapper<CustomerNotFoundException> {
    override fun toResponse(error: CustomerNotFoundException?): Response = Response.status(400)
        .entity(
            ProblemDetails(
                "https://pitstop.infosupport.com/exceptions/missing-association",
                "Required association not found",
                "Customer with ID ${error?.customerId} not found."
            )
        )
        .build()
}