package com.infosupport.pitstop.invoicing.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Vehicle protected constructor() {
    @Id
    var id: Long? = null
    lateinit var licenseNumber : String
    lateinit var brand : String
    lateinit var type : String
    var ownerId: Long = 0

    constructor(id: Long, licenseNumber: String, brand: String, type: String, ownerId: Long) : this() {
        this.id = id
        this.licenseNumber = licenseNumber
        this.brand = brand
        this.type = type
        this.ownerId = ownerId
    }
}