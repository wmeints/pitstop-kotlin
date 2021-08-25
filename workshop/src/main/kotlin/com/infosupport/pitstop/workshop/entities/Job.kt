package com.infosupport.pitstop.workshop.entities

import javax.persistence.*

@Entity
class Job protected constructor() {
    constructor(vehicle: Vehicle, customer: Customer, description: String, timeslot:Timeslot): this() {
        this.customer = customer
        this.vehicle = vehicle
        this.description = description
        this.plannedTimeslot = timeslot
    }

    fun complete(actualTimeslot: Timeslot, notes:String) {
        this.notes = notes
        this.actualTimeslot = actualTimeslot
    }

    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "vehicleid", foreignKey = ForeignKey(name = "fk_job_vehicle"))
    lateinit var vehicle: Vehicle

    @ManyToOne
    @JoinColumn(name = "customerid", foreignKey = ForeignKey(name = "fk_job_customer"))
    lateinit var customer: Customer

    lateinit var description: String

    @Embedded()
    @AttributeOverrides(
        AttributeOverride(name = "startDate", column = Column(name = "plannedstartdate")),
        AttributeOverride(name = "endDate", column = Column(name = "plannedenddate"))
    )
    lateinit var plannedTimeslot: Timeslot

    @Embedded()
    @AttributeOverrides(
        AttributeOverride(name = "startDate", column = Column(name = "actualstartdate")),
        AttributeOverride(name = "endDate", column = Column(name = "actualenddate"))
    )
    var actualTimeslot: Timeslot? = null

    var notes: String? = null

    val status: String get() = when (actualTimeslot) {
        null -> "Planned"
        else -> "Completed"
    }
}