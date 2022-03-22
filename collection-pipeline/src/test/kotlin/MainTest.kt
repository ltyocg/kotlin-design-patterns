import org.slf4j.LoggerFactory
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class MainTest {
    private val log = LoggerFactory.getLogger(javaClass)
    private val cars = CarFactory.createCars()

    @Test
    fun `test getModelsAfter2000`() {
        assertContentEquals(listOf("Avenger", "Wrangler", "Focus", "Cascada"), cars.getModelsAfter2000())
    }

    @Test
    fun `test getGroupingOfCarsByCategory`() {
        val models = cars.getGroupingOfCarsByCategory()
        log.info("Category {}", models)
        assertEquals(
            mapOf(
                Category.CONVERTIBLE to listOf(
                    Car("Buick", "Cascada", 2016, Category.CONVERTIBLE),
                    Car("Chevrolet", "Geo Metro", 1992, Category.CONVERTIBLE)
                ),
                Category.SEDAN to listOf(
                    Car("Dodge", "Avenger", 2010, Category.SEDAN),
                    Car("Ford", "Focus", 2012, Category.SEDAN)
                ),
                Category.JEEP to listOf(
                    Car("Jeep", "Wrangler", 2011, Category.JEEP),
                    Car("Jeep", "Comanche", 1990, Category.JEEP)
                )
            ), models
        )
    }

    @Test
    fun `test getSedanCarsOwnedSortedByDate`() {
        assertContentEquals(
            listOf(
                Car("Dodge", "Avenger", 2010, Category.SEDAN),
                Car("Ford", "Focus", 2012, Category.SEDAN)
            ), listOf(Person(cars)).getSedanCarsOwnedSortedByDate()
        )
    }
}