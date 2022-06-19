import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CashAAATest {
    @Test
    fun plus() = assertEquals(7, Cash(3).also { it.plus(4) }.count())

    @Test
    fun minus() = assertEquals(3, Cash(8).also { it.minus(5) }.count())

    @Test
    fun `insufficient minus`() {
        val cash = Cash(1)
        assertFalse(cash.minus(6))
        assertEquals(1, cash.count())
    }

    @Test
    fun update() {
        val cash = Cash(5).also { it.plus(6) }
        assertTrue(cash.minus(3))
        assertEquals(8, cash.count())
    }
}