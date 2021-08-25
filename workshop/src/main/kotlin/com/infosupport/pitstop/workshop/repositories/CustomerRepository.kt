package com.infosupport.pitstop.workshop.repositories

import com.infosupport.pitstop.workshop.entities.Customer
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomerRepository : PanacheRepository<Customer> {

}