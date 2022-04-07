import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class CustomerRegistryTest {
    @Test
    fun `should be able to add and query customer object from registry`() {
        CustomerRegistry.addCustomer(Customer("1", "john"))
        CustomerRegistry.addCustomer(Customer("2", "julia"))
        val customerWithId1 = CustomerRegistry.getCustomer("1")
        assertNotNull(customerWithId1)
        assertEquals("1", customerWithId1.id)
        assertEquals("john", customerWithId1.name)
        val customerWithId2 = CustomerRegistry.getCustomer("2")
        assertNotNull(customerWithId2)
        assertEquals("2", customerWithId2.id)
        assertEquals("julia", customerWithId2.name)
    }

    @Test
    fun `should return null when queried customer is not in registry`() =
        assertNull(CustomerRegistry.getCustomer("5"))
}