package com.infosupport.pitstop.invoicing.repositories

import com.infosupport.pitstop.invoicing.entities.Vehicle
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class VehicleRepository: PanacheRepository<Vehicle> {

}