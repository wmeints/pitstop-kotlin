package com.infosupport.pitstop.workshop.services

import com.infosupport.pitstop.workshop.entities.Job
import com.infosupport.pitstop.workshop.events.JobCompleted
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class EventPublisher(private val jobCompletedEmitter: Emitter<JobCompleted>) {
    fun publishJobCompleted(job: Job) {
        val actualTimeslot = job.actualTimeslot ?: throw IllegalArgumentException("Job is not yet completed.")

        jobCompletedEmitter.send(
            JobCompleted(
                job.id ?: 0,
                job.vehicle.id ?: 0,
                job.customer.id ?: 0,
                actualTimeslot.startDate,
                actualTimeslot.endDate
            )
        )
    }
}