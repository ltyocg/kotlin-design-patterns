import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HalfArithmeticTest {
    private companion object {
        private val arithmetic = HalfArithmetic(HalfSource, OldSource)
    }

    @Test
    fun sum() = assertEquals(0, arithmetic.sum(-1, 0, 1))

    @Test
    fun mul() = assertEquals(0, arithmetic.mul(-1, 0, 1))

    @Test
    fun ifHasZero() = assertTrue(arithmetic.ifHasZero(-1, 0, 1))
}