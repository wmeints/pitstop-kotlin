package com.infosupport.pitstop.customers

import io.quarkus.panache.common.Page
import java.net.URI
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

@Path("/customers")
class CustomersResource {
    @GET
    @Produces(APPLICATION_JSON)
    fun list(@QueryParam("page") page : Int): PagedResult<Customer> {
        val customers = Customer.findAll().page(Page(page, 20)).list()
        val totalCustomers = Customer.count()

        return PagedResult<Customer>(customers, page, 20, totalCustomers)
    }

    @GET
    @Path("/{id}")
    @Produces(APPLICATION_JSON)
    fun details(@PathParam("id") id : Long) : Response {

        return when(val customer = Customer.findById(id)) {
            null -> Response.status(404).build()
            else -> Response.ok(customer).build()
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    fun register(cmd : RegisterCustomerCommand) : Response {
        val customer = Customer(cmd.name, cmd.address, cmd.zipCode,cmd.city,cmd.telephoneNumber,cmd.emailAddress)
        customer.persist()

        return Response.created(URI.create("customers/${customer.id}")).build()
    }
}