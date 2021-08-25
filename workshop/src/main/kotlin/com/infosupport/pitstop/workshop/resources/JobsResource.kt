package com.infosupport.pitstop.workshop.resources

import com.infosupport.pitstop.workshop.commands.RegisterJobCommand
import com.infosupport.pitstop.workshop.entities.Timeslot
import com.infosupport.pitstop.workshop.entities.Vehicle
import com.infosupport.pitstop.workshop.repositories.CustomerRepository
import com.infosupport.pitstop.workshop.repositories.JobRepository
import com.infosupport.pitstop.workshop.repositories.VehicleRepository
import com.infosupport.pitstop.workshop.services.WorkshopPlanning
import java.net.URI
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

@Path("/jobs")
class JobsResource(
    private val jobRepository: JobRepository,
    private val vehicleRepository: VehicleRepository,
    private val customerRepository: CustomerRepository
) {
    @POST
    @Consumes(APPLICATION_JSON)
    fun register(cmd: RegisterJobCommand): Response {
        val planning = WorkshopPlanning(
            cmd.startDate.toLocalDate(),
            jobRepository.findForDay(cmd.startDate.toLocalDate()).list()
        )

        val vehicle = vehicleRepository.findById(cmd.vehicleId)
        val customer = customerRepository.findById(cmd.customerId)

        if(vehicle == null) {
            throw IllegalArgumentException("Can't find specified vehicle")
        }

        if(customer == null) {
            throw IllegalArgumentException("Can't find specified customer")
        }

        val job = planning.scheduleJob(vehicle,customer, Timeslot(cmd.startDate, cmd.endDate), cmd.description)
        jobRepository.persist(job)

        return Response.accepted().build()
    }
}