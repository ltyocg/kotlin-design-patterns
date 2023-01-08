import java.io.*
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CountryTest {
    @Test
    fun serializable() {
        ObjectOutputStream(FileOutputStream("output.txt")).use {
            it.writeObject(Country(86, "China", "Asia", "Chinese"))
        }
        val country = ObjectInputStream(FileInputStream("output.txt")).use {
            it.readObject() as Country
        }
        println(country)
        assertEquals(Country(86, "China", "Asia", "Chinese"), country)
    }

    @AfterTest
    fun cleanup() {
        File("output.txt").delete()
    }
}