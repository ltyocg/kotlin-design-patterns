import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FindCustomerTest {
    @Test
    fun `no exceptions`() = assertEquals(FindCustomer("123").perform(), "123")

    @Test
    fun `one exception`() {
        val findCustomer = FindCustomer("123", BusinessException("test"))
        assertFailsWith<BusinessException> { findCustomer.perform() }
    }

    @Test
    fun `result after exceptions`() {
        val op = FindCustomer(
            "123",
            CustomerNotFoundException("not found"),
            DatabaseNotAvailableException("not available")
        )
        try {
            op.perform()
        } catch (_: CustomerNotFoundException) {
        }
        try {
            op.perform()
        } catch (_: DatabaseNotAvailableException) {
        }
        assertEquals(op.perform(), "123")
    }
}