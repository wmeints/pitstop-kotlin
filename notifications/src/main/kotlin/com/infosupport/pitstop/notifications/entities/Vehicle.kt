package com.infosupport.pitstop.notifications.entities

class Vehicle {
    var id: Long? = null
    lateinit var licenseNumber : String
    lateinit var brand : String
    lateinit var type : String
    var ownerId: Long = 0
}