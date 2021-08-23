package com.infosupport.pitstop.vehicles

data class RegisterVehicleCommand(val licenseNumber: String, val brand: String, val type: String, val ownerId: Int)
