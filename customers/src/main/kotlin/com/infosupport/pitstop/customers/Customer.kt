package com.infosupport.pitstop.customers

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity
class Customer() : PanacheEntity() {
    lateinit var name: String
    lateinit var address: String
    lateinit var zipCode: String
    lateinit var city: String
    lateinit var telephoneNumber: String
    lateinit var emailAddress: String

    constructor(
        name: String,
        address: String,
        zipCode: String,
        city: String,
        telephoneNumber: String,
        emailAddress: String
    ) : this() {
        this.name = name
        this.address = address
        this.zipCode = zipCode
        this.city = city
        this.telephoneNumber = telephoneNumber
        this.emailAddress = emailAddress
    }

    companion object : PanacheCompanion<Customer> {

    }
}