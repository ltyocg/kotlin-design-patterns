import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    val cars = CarFactory.createCars()
    log.info(cars.getModelsAfter2000().toString())
    log.info(cars.getGroupingOfCarsByCategory().toString())
    log.info(listOf(Person(cars)).getSedanCarsOwnedSortedByDate().toString())
}