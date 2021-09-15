package com.ltyocg.domainmodel

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.sql.ResultSet
import java.time.LocalDate
import javax.sql.DataSource
import kotlin.test.*

private const val INSERT_CUSTOMER_SQL = "insert into CUSTOMERS values('customer', 100)"
private const val SELECT_CUSTOMERS_SQL = "select name, money from CUSTOMERS"
private const val INSERT_PURCHASES_SQL = "insert into PURCHASES values('product', 'customer')"
private const val SELECT_PURCHASES_SQL = "select product_name, customer_name from PURCHASES"

class CustomerDaoImplTest {
    private lateinit var dataSource: DataSource
    private lateinit var customerDao: CustomerDao
    private lateinit var customer: Customer
    private lateinit var product: Product

    @BeforeTest
    fun setUp() {
        dataSource = Utils.createDataSource()
        Utils.deleteSchema(dataSource)
        Utils.createSchema(dataSource)
        customerDao = CustomerDaoImpl(dataSource)
        customer = Customer(customerDao, "customer", Money.of(CurrencyUnit.USD, 100.0))
        product = Product(ProductDaoImpl(dataSource), "product", Money.of(CurrencyUnit.USD, 100.0), LocalDate.parse("2021-06-27"))
    }

    @AfterTest
    fun tearDown() {
        Utils.deleteSchema(dataSource)
    }

    @Test
    fun `should find customer by name`() {
        assertNull(customerDao.findByName("customer"))
        Utils.executeSQL(INSERT_CUSTOMER_SQL, dataSource)
        val customer = customerDao.findByName("customer")
        assertNotNull(customer)
        assertEquals("customer", customer.name)
        assertEquals(Money.of(CurrencyUnit.USD, 100.0), customer.money)
    }

    @Test
    fun `should save customer`() {
        customerDao.save(customer)
        resultSet(SELECT_CUSTOMERS_SQL) {
            assertTrue(next())
            assertEquals(customer.name, getString("name"))
            assertEquals(customer.money, Money.of(CurrencyUnit.USD, getBigDecimal("money")))
            assertFalse(next())
        }
    }

    @Test
    fun `should add product to purchases`() {
        Utils.executeSQL(INSERT_CUSTOMER_SQL, dataSource)
        Utils.executeSQL(INSERT_PRODUCT_SQL, dataSource)
        customerDao.addProduct(product, customer)
        resultSet(SELECT_PURCHASES_SQL) {
            assertTrue(next())
            assertEquals(product.name, getString("product_name"))
            assertEquals(customer.name, getString("customer_name"))
            assertFalse(next())
        }
    }

    @Test
    fun `should delete product from purchases`() {
        Utils.executeSQL(INSERT_CUSTOMER_SQL, dataSource)
        Utils.executeSQL(INSERT_PRODUCT_SQL, dataSource)
        Utils.executeSQL(INSERT_PURCHASES_SQL, dataSource)
        customerDao.deleteProduct(product, customer)
        resultSet(SELECT_PURCHASES_SQL) {
            assertFalse(next())
        }
    }

    private fun resultSet(sql: String, block: ResultSet.() -> Unit) {
        dataSource.connection.use { it.createStatement().use { statement -> block(statement.executeQuery(sql)) } }
    }
}