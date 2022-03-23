import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class NazgulTest {
    @Test
    fun getInstance() = NazgulName.values().forEach {
        val nazgul = Nazgul.getInstance(it)
        assertSame(nazgul, Nazgul.getInstance(it))
        assertEquals(it, nazgul.name)
    }
}