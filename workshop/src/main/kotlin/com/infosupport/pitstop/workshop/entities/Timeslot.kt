package com.infosupport.pitstop.workshop.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Embeddable

@Embeddable
class Timeslot protected constructor() {
    constructor(startDate: LocalDateTime, endDate: LocalDateTime): this() {
        if(startDate > endDate) {
            throw IllegalArgumentException("Make sure that endDate is after startDate")
        }

        if(startDate == endDate) {
            throw IllegalArgumentException("Empty timeslots are not allowed")
        }

        this.startDate = startDate
        this.endDate = endDate
    }

    lateinit var startDate: LocalDateTime
    lateinit var endDate: LocalDateTime

    fun overlaps(other: Timeslot): Boolean =
        startDate > other.startDate && endDate <= other.endDate ||
                endDate > other.startDate && endDate <= other.endDate

    fun startsBefore(dateTime: LocalDateTime) = startDate < dateTime

    fun startsAfter(dateTime: LocalDateTime) = startDate > dateTime

    fun endsAfter(dateTime: LocalDateTime) = endDate > dateTime

    fun endsBefore(dateTime: LocalDateTime) = endDate < dateTime

    override fun toString(): String {
        return "$startDate - $endDate"
    }

    override fun hashCode(): Int {
        return Objects.hash(startDate, endDate)
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Timeslot -> startDate == other.startDate && endDate == other.endDate
            else -> false
        }
    }
}