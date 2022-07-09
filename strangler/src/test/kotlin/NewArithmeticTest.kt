import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NewArithmeticTest {
    private companion object {
        private val arithmetic = NewArithmetic(NewSource)
    }

    @Test
    fun sum() = assertEquals(0, arithmetic.sum(-1, 0, 1))

    @Test
    fun mul() = assertEquals(0, arithmetic.mul(-1, 0, 1))

    @Test
    fun ifHasZero() = assertTrue(arithmetic.ifHasZero(-1, 0, 1))
}