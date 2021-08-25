package com.infosupport.pitstop.workshop.entities

import com.github.javafaker.Faker
import com.infosupport.pitstop.workshop.services.WorkshopPlanning
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

object Timeslots {
    fun random(day: LocalDate): Timeslot {
        val start = day.atTime((9..16).random(),0)
        val end = start.plus(1, ChronoUnit.HOURS)

        return Timeslot(start, end)
    }
}

object Workshop {
    private val faker = Faker()

    private fun randomDay() = faker.date()
        .future(14, TimeUnit.DAYS)
        .toInstant()
        .atOffset(ZoneOffset.UTC)
        .toLocalDate()

    fun emptyPlanning() = WorkshopPlanning(randomDay(), emptyList())

    fun fullyBooked(): WorkshopPlanning {
        val day = randomDay()

        val jobs = (8..16).flatMap { hour ->
            (1..4).map {
                val customer = Customers.random()
                val vehicle = Vehicles.random(customer)

                val timeslot = Timeslot(
                    LocalDateTime.of(day, LocalTime.of(hour,0)),
                    LocalDateTime.of(day, LocalTime.of(hour + 1, 0))
                )

                Job(vehicle, customer, faker.gameOfThrones().quote(), timeslot)
            }
        }

        return WorkshopPlanning(day, jobs)
    }
}

object Jobs {
    private val faker = Faker()

    fun random(day: LocalDate): Job {
        val customer = Customers.random()
        val vehicle = Vehicles.random(customer)

        val minDate = day.atStartOfDay()
        val maxDate = day.atTime(23, 0)

        val startDate = LocalDateTime.from(
            faker.date().between(
                Date.from(minDate.toInstant(ZoneOffset.UTC)),
                Date.from(maxDate.toInstant(ZoneOffset.UTC))
            ).toInstant()
        )

        val endDate = startDate.plus(1, ChronoUnit.HOURS)

        return Job(vehicle, customer, faker.gameOfThrones().quote(), Timeslot(startDate, endDate))
    }
}

object Customers {
    private val faker = Faker(Locale("nl"))

    fun random(): Customer = Customer(
        Random.nextLong(1000),
        faker.name().fullName(),
        faker.address().streetAddress(),
        faker.address().zipCode(),
        faker.address().city(),
        faker.phoneNumber().cellPhone(),
        faker.bothify("????##@domain.org")
    )
}

object Vehicles {
    private val faker = Faker()

    private val brands = listOf(
        "Renault",
        "Ford",
        "Nissan"
    )

    private val types = mapOf(
        "Renault" to listOf("Kadjar", "Clio", "Arkana"),
        "Ford" to listOf("Focus", "Fiesta", "Ecosport", "Kuga"),
        "Nissan" to listOf("Micra", "Leaf", "Qashqai")
    )

    fun random(owner: Customer): Vehicle {
        val brand = brands.random()
        val type = types[brand]?.random() ?: "Generic"

        return Vehicle(1L, faker.bothify("?-###-??"), brand, type, owner.id ?: 0)
    }
}