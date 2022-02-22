import org.h2.jdbcx.JdbcDataSource
import org.junit.jupiter.api.Nested
import org.mockito.kotlin.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import javax.sql.DataSource
import kotlin.test.*

class DbCustomerDaoTest {
    private val nonExistingCustomerId = 999
    private lateinit var dao: DbCustomerDao
    private val existingCustomer = Customer(1, "Freddy", "Krueger")

    @BeforeTest
    fun createSchema() {
        DriverManager.getConnection(DB_URL).use { it.createStatement().use { statement -> statement.execute(CustomerSchemaSql.CREATE_SCHEMA_SQL) } }
    }

    @Nested
    inner class ConnectionSuccess {
        @BeforeTest
        fun setUp() {
            dao = DbCustomerDao(JdbcDataSource().apply { setURL(DB_URL) })
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
            fun `retrieve should return no customer`() {
                assertNull(dao.getById(nonExistingCustomerId))
            }
        }

        @Nested
        inner class ExistingCustomer {
            @Test
            fun `adding should result in failure and not affect existing customers`() {
                val existingCustomer = Customer(1, "Freddy", "Krueger")
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
                val customer = Customer(existingCustomer.id, newFirstname, newLastname)
                assertTrue(dao.update(customer))
                val cust = dao.getById(existingCustomer.id)!!
                assertEquals(newFirstname, cust.firstName)
                assertEquals(newLastname, cust.lastName)
            }
        }
    }

    @Nested
    inner class ConnectivityIssue {
        @BeforeTest
        fun setUp() {
            val mockedDataSource = mock<DataSource>()
            val mockedConnection = mock<Connection>()
            doThrow(SQLException("Connection not available")).whenever(mockedConnection).prepareStatement(any())
            doReturn(mockedConnection).whenever(mockedDataSource).connection
            dao = DbCustomerDao(mockedDataSource)
        }

        @Test
        fun `adding a customer fails with exception as feedback to client`() {
            assertFailsWith<Exception> { dao.add(Customer(2, "Bernard", "Montgomery")) }
        }

        @Test
        fun `deleting a customer fails with exception as feedback to client`() {
            assertFailsWith<Exception> { dao.delete(existingCustomer) }
        }

        @Test
        fun `updating a customer fails with feedback to the client`() {
            assertFailsWith<Exception> { dao.update(Customer(existingCustomer.id, "Bernard", "Montgomery")) }
        }

        @Test
        fun `retrieving a customer by id fails with exception as feedback to client`() {
            assertFailsWith<Exception> { dao.getById(existingCustomer.id) }
        }

        @Test
        fun `retrieving all customers fails with exception as feedback to client`() {
            assertFailsWith<Exception> { dao.all.count() }
        }
    }

    @AfterTest
    fun `delete schema`() {
        DriverManager.getConnection(DB_URL).use { it.createStatement().use { statement -> statement.execute(CustomerSchemaSql.DELETE_SCHEMA_SQL) } }
    }

    private fun assertCustomerCountIs(count: Int) {
        assertEquals(count, dao.all.count())
    }
}