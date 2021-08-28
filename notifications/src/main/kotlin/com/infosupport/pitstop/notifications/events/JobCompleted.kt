package com.infosupport.pitstop.notifications.events

import com.infosupport.pitstop.notifications.entities.Customer
import com.infosupport.pitstop.notifications.entities.Vehicle
import java.time.LocalDateTime
import java.util.*

data class JobCompleted(
    val jobId: Long,
    val vehicle: Vehicle,
    val customer: Customer,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val description: String,
    val notes: String
)

