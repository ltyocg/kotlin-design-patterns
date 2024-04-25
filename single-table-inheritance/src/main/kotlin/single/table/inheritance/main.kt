package single.table.inheritance

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import single.table.inheritance.entity.Car
import single.table.inheritance.entity.Truck
import single.table.inheritance.service.VehicleService

@SpringBootApplication
class SingleTableInheritance(private val vehicleService: VehicleService) : CommandLineRunner {
    private val logger = KotlinLogging.logger {}
    override fun run(vararg args: String) {
        logger.info { "Saving Vehicles :- " }
        val vehicle1 = Car("Tesla", "Model S", 4, 825)
        logger.info { "Vehicle 1 saved : ${vehicleService.saveVehicle(vehicle1)}" }
        val vehicle2 = Truck("Ford", "F-150", 3325, 14000)
        logger.info { "Vehicle 2 saved : ${vehicleService.saveVehicle(vehicle2)}\n" }
        logger.info { "Fetching Vehicles :- " }
        logger.info { "Fetching Car1 from DB : ${vehicleService.getVehicle(vehicle1.vehicleId)}" }
        logger.info { "Fetching Truck1 from DB : ${vehicleService.getVehicle(vehicle2.vehicleId)}\n" }
        logger.info { "Fetching All Vehicles :- " }
        vehicleService.allVehicles.forEach { logger.info { it } }
    }
}

fun main(args: Array<String>) {
    runApplication<SingleTableInheritance>(*args)
}
