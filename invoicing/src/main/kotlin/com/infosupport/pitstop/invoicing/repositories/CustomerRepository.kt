package com.infosupport.pitstop.invoicing.repositories

import com.infosupport.pitstop.invoicing.entities.Customer
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CustomerRepository : PanacheRepository<Customer> {

}