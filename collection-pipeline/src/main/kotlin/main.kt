import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val cars = CarFactory.createCars()
    logger.info { cars.getModelsAfter2000() }
    logger.info { cars.getGroupingOfCarsByCategory() }
    logger.info { listOf(Person(cars)).getSedanCarsOwnedSortedByDate() }
}
