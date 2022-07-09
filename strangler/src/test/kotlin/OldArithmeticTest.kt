import kotlin.test.Test
import kotlin.test.assertEquals

class OldArithmeticTest {
    private companion object {
        private val arithmetic = OldArithmetic(OldSource)
    }

    @Test
    fun sum() = assertEquals(0, arithmetic.sum(-1, 0, 1))

    @Test
    fun mul() = assertEquals(0, arithmetic.mul(-1, 0, 1))
}