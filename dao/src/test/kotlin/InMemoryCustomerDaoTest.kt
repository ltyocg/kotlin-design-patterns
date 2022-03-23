import org.junit.jupiter.api.Nested
import kotlin.test.*

class InMemoryCustomerDaoTest {
    private val nonExistingCustomerId = 999
    private lateinit var dao: InMemoryCustomerDao
    private val existingCustomer = Customer(1, "Freddy", "Krueger")

    @BeforeTest
    fun setUp() {
        dao = InMemoryCustomerDao()
        assertTrue(dao.add(existingCustomer))
    }

    @Nested
    inner class NonExistingCustomer {
        @Test
        fun `adding should result in success`() {
            assertCustomerCountIs(1)
            val nonExistingCustomer = Customer(2, "Robert", "Englund")
            assertTrue(dao.add(nonExistingCustomer))
            assertCustomerCountIs(2)
            assertEquals(nonExistingCustomer, dao.getById(nonExistingCustomer.id))
        }

        @Test
        fun `deletion should be failure and not affect existing customers`() {
            assertFalse(dao.delete(Customer(2, "Robert", "Englund")))
            assertCustomerCountIs(1)
        }

        @Test
        fun `updation should be failure and not affect existing customers`() {
            assertFalse(dao.update(Customer(nonExistingCustomerId, "Douglas", "MacArthur")))
            assertNull(dao.getById(nonExistingCustomerId))
        }

        @Test
        fun `retrieve should return no customer`() = assertNull(dao.getById(nonExistingCustomerId))
    }

    @Nested
    inner class ExistingCustomer {
        @Test
        fun `adding should result in failure and not affect existing customers`() {
            assertFalse(dao.add(existingCustomer))
            assertCustomerCountIs(1)
            assertEquals(existingCustomer, dao.getById(existingCustomer.id))
        }

        @Test
        fun `deletion should be success and customer should be non accessible`() {
            assertTrue(dao.delete(existingCustomer))
            assertCustomerCountIs(0)
            assertNull(dao.getById(existingCustomer.id))
        }

        @Test
        fun `updation should be success and accessing the same customer should return updated information`() {
            val newFirstname = "Bernard"
            val newLastname = "Montgomery"
            assertTrue(dao.update(Customer(existingCustomer.id, newFirstname, newLastname)))
            val cust = dao.getById(existingCustomer.id)!!
            assertEquals(newFirstname, cust.firstName)
            assertEquals(newLastname, cust.lastName)
        }

        @Test
        fun `retrieve should return the customer`() = assertEquals(existingCustomer, dao.getById(existingCustomer.id))
    }

    private fun assertCustomerCountIs(count: Int) = assertEquals(count, dao.all.count())
}