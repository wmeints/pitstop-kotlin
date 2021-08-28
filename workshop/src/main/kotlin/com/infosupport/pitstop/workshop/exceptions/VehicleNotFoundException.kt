package com.infosupport.pitstop.workshop.exceptions

class VehicleNotFoundException(val vehicleId: Long) : Exception("Vehicle with ID $vehicleId could not be found.") {

}