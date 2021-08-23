package com.infosupport.pitstop.invoicing.entities

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.annotation.processing.Generated
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Customer protected constructor() {
    @Id
    var id: Long? = null
    lateinit var name: String
    lateinit var address: String
    lateinit var zipCode: String
    lateinit var city: String
    lateinit var telephoneNumber: String
    lateinit var emailAddress: String

    constructor(
        id: Long,
        name: String,
        address: String,
        zipCode: String,
        city: String,
        telephoneNumber: String,
        emailAddress: String
    ) : this() {
        this.id = id
        this.name = name
        this.address = address
        this.zipCode = zipCode
        this.city = city
        this.telephoneNumber = telephoneNumber
        this.emailAddress = emailAddress
    }
}