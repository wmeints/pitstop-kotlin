package com.infosupport.pitstop.vehicles

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity
class Vehicle() : PanacheEntity() {
    lateinit var licenseNumber : String
    lateinit var brand : String
    lateinit var type : String
    var ownerId: Int = 0

    constructor(licenseNumber: String, brand: String, type: String, ownerId: Int) : this() {
        this.licenseNumber = licenseNumber
        this.brand = brand
        this.type = type
        this.ownerId = ownerId
    }

    companion object : PanacheCompanion<Vehicle> {
        fun findByLicenseNumber(licenseNumber: String) = find("licenseNumber", licenseNumber).firstResult()
    }
}