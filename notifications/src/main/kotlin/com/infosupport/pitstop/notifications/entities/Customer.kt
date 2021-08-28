package com.infosupport.pitstop.notifications.entities

class Customer {
    var id: Long? = null
    lateinit var name: String
    lateinit var address: String
    lateinit var zipCode: String
    lateinit var city: String
    lateinit var telephoneNumber: String
    lateinit var emailAddress: String
}
