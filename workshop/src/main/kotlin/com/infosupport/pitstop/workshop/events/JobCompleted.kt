package com.infosupport.pitstop.workshop.events

import java.time.LocalDateTime
import java.util.*

data class JobCompleted(
    val jobId: Long,
    val vehicleId: Long,
    val customerId: Long,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime
)
