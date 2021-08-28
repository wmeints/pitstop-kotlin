package com.infosupport.pitstop.workshop.exceptions

class CustomerNotFoundException(val customerId: Long): Exception("Customer with ID $customerId not found")