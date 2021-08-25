package com.infosupport.pitstop.workshop.entities

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.stream.Stream

class TimeslotTests {
    @ParameterizedTest
    @MethodSource("overlappingTimeslots")
    fun testOverlaps(first: Timeslot, second: Timeslot) {
        assertThat(first.overlaps(second), `is`(true))
    }

    @ParameterizedTest
    @MethodSource("nonOverlappingTimeslots")
    fun testDoesntOverlap(first: Timeslot, second: Timeslot) {
        assertThat(first.overlaps(second), `is`(false))
    }

    @Test
    fun testDateTimesMustBeInOrder() {
        assertThrows<IllegalArgumentException> {
            Timeslot(
                LocalDateTime.of(2021, 5, 1, 11, 0, 0),
                LocalDateTime.of(2021, 5, 1, 10, 0, 0)
            )
        }
    }

    @Test
    fun testEmptyTimeslotsAreNotAllowed() {
        assertThrows<IllegalArgumentException> {
            Timeslot(
                LocalDateTime.of(2021, 5, 1, 10, 0, 0),
                LocalDateTime.of(2021, 5, 1, 10, 0, 0)
            )
        }
    }

    @Test
    fun testConstructValidTimeslot() {
        val startDate = LocalDateTime.of(2021, 5, 1, 10, 0, 0)
        val endDate = LocalDateTime.of(2021, 5, 1, 11, 0, 0)

        val timeslot = Timeslot(
            startDate,
            endDate
        )

        assertThat(timeslot.startDate, `is`(startDate))
        assertThat(timeslot.endDate, `is`(endDate))
    }

    companion object TimeslotTests {
        @JvmStatic
        fun overlappingTimeslots(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Timeslot(
                        LocalDateTime.of(2021, 5, 1, 10, 0, 0),
                        LocalDateTime.of(2021, 5, 1, 10, 30, 0)
                    ),
                    Timeslot(
                        LocalDateTime.of(2021, 5, 1, 10, 0, 0),
                        LocalDateTime.of(2021, 5, 1, 11, 0, 0, 0)
                    )
                ),
                Arguments.of(
                    Timeslot(
                        LocalDateTime.of(2021, 5, 1, 10, 30, 0),
                        LocalDateTime.of(2021, 5, 1, 11, 0, 0)
                    ),
                    Timeslot(
                        LocalDateTime.of(2021, 5, 1, 10, 0, 0),
                        LocalDateTime.of(2021, 5, 1, 11, 0, 0)
                    )
                ),
            )
        }

        @JvmStatic
        fun nonOverlappingTimeslots(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Timeslot(
                        LocalDateTime.of(2021, 5, 1, 10, 0, 0),
                        LocalDateTime.of(2021, 5, 1, 10, 30, 0)
                    ),
                    Timeslot(
                        LocalDateTime.of(2021, 5, 1, 10, 30, 0),
                        LocalDateTime.of(2021, 5, 1, 11, 0, 0, 0)
                    )
                ),
            )
        }
    }
}