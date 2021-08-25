package com.infosupport.pitstop.workshop.entities

import com.infosupport.pitstop.workshop.services.WorkshopPlanning
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Collections.copy

class WorkshopPlanningTests {
    @Test
    fun testCantPlanOnInvalidDay() {
        val planning = Workshop.emptyPlanning()
        val customer = Customers.random()
        val vehicle = Vehicles.random(customer)

        val error = assertThrows<IllegalArgumentException> {
            planning.scheduleJob(
                vehicle,
                customer,
                Timeslots.random(planning.date.plusDays(1)),
                "Fix engine"
            )
        }

        assertThat(error.message, `is`("Slot must fit within the workday of the workshop"))
    }

    @Test
    fun testPlanRegularJob() {
        val planning = Workshop.emptyPlanning()
        val customer = Customers.random()
        val vehicle = Vehicles.random(customer)

        val job = planning.scheduleJob(vehicle, customer, Timeslots.random(planning.date), "Fix engine")

        assertThat(job.description, `is`("Fix engine"))
        assertThat(job.customer, `is`(customer))
        assertThat(job.vehicle, `is`(vehicle))
        assertThat(job.status, `is`("Planned"))
    }

    @Test
    fun testCompleteJob() {
        val planning = Workshop.emptyPlanning()
        val customer = Customers.random()
        val vehicle = Vehicles.random(customer)

        val job = planning.scheduleJob(vehicle, customer, Timeslots.random(planning.date), "Fix engine")
        job.complete(job.plannedTimeslot, "Fixed it!")

        assertThat(job.actualTimeslot, `is`(job.plannedTimeslot))
        assertThat(job.status, `is`("Completed"))
        assertThat(job.notes, `is`("Fixed it!"))
    }

    @Test
    fun testCantPlanTooManyJobs() {
        val customer = Customers.random()
        val vehicle = Vehicles.random(customer)

        val planning = Workshop.fullyBooked()

        val error = assertThrows<IllegalArgumentException> {
            planning.scheduleJob(
                vehicle,
                customer,
                Timeslots.random(planning.date),
                "Fix engine"
            )
        }

        assertThat(
            error.message,
            `is`("Can't plan more than ${WorkshopPlanning.MAX_PARALLEL_JOBS} in one timeslot")
        )
    }
}