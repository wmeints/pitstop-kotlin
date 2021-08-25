package com.infosupport.pitstop.workshop.repositories

import com.infosupport.pitstop.workshop.entities.Job
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class JobRepository: PanacheRepository<Job> {
    fun findForDay(date: LocalDate) = find(
        "SELECT jobs from Job jobs WHERE jobs.plannedTimeslot.startDate >= ?1 AND jobs.plannedTimeslot.endDate <= ?2",
        LocalDateTime.of(date, LocalTime.of(0,0,0)),
        LocalDateTime.of(date, LocalTime.of(23,59,59)))
}