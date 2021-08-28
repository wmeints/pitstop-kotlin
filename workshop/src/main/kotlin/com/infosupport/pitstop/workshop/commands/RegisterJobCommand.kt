package com.infosupport.pitstop.workshop.commands

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import java.time.LocalDateTime
import java.util.*

data class RegisterJobCommand(
    val vehicleId: Long,
    val customerId: Long,

    val startDate: LocalDateTime,

    val endDate: LocalDateTime,

    val description: String
)