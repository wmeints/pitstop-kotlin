package com.infosupport.pitstop.workshop.services

import com.infosupport.pitstop.workshop.entities.Customer
import com.infosupport.pitstop.workshop.entities.Job
import com.infosupport.pitstop.workshop.entities.Timeslot
import com.infosupport.pitstop.workshop.entities.Vehicle
import com.infosupport.pitstop.workshop.repositories.CustomerRepository
import com.infosupport.pitstop.workshop.repositories.JobRepository
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


class WorkshopPlanning (val date: LocalDate, jobs: List<Job>) {
    private val jobs = jobs.toMutableList()

    fun scheduleJob(vehicle: Vehicle, customer: Customer, slot: Timeslot, description: String): Job {
        val startOfDay = LocalDateTime.of(date, LocalTime.of(0,0,0))
        val endOfDay = LocalDateTime.of(date, LocalTime.of(16,0,0))

        if(slot.startsBefore(startOfDay) || slot.startsAfter(endOfDay) || slot.endsBefore(startOfDay) || slot.endsAfter(endOfDay)) {
            throw IllegalArgumentException("Slot must fit within the workday of the workshop")
        }

        val parallelJobs = jobs.count { job ->
            slot.overlaps(job.plannedTimeslot)
        }

        if(parallelJobs + 1 > MAX_PARALLEL_JOBS) {
            throw IllegalArgumentException("Can't plan more than $MAX_PARALLEL_JOBS in one timeslot")
        }

        val job = Job(vehicle, customer, description, slot)
        jobs.add(job)

        return job
    }

    companion object {
        const val MAX_PARALLEL_JOBS = 4
    }
}

