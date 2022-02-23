import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class InMemoryBankTest {
    private val bank = InMemoryBank()

    @Test
    fun `test init`() {
        assertEquals(0, bank.getFunds("foo"))
        bank.setFunds("foo", 100)
        assertEquals(100, bank.getFunds("foo"))
        bank.setFunds("bar", 150)
        assertEquals(150, bank.getFunds("bar"))
        assertTrue(bank.transferFunds(50, "bar", "foo"))
        assertEquals(150, bank.getFunds("foo"))
        assertEquals(100, bank.getFunds("bar"))
    }
}