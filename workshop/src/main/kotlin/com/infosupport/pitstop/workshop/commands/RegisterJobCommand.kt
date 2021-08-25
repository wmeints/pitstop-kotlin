package com.infosupport.pitstop.workshop.commands

import java.time.LocalDateTime

data class RegisterJobCommand(
    val vehicleId: Long,
    val customerId: Long,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val description: String
)