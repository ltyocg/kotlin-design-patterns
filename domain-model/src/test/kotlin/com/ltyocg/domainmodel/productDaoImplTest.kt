package com.ltyocg.domainmodel

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.sql.ResultSet
import java.sql.SQLException
import java.time.LocalDate
import javax.sql.DataSource
import kotlin.test.*

const val INSERT_PRODUCT_SQL = "insert into PRODUCTS values('product', 100, DATE '2021-06-27')"
private const val SELECT_PRODUCTS_SQL = "select name, price, expiration_date from PRODUCTS"

class ProductDaoImplTest {
    private lateinit var dataSource: DataSource
    private lateinit var productDao: ProductDao
    private lateinit var product: Product

    @BeforeTest
    fun setUp() {
        dataSource = Utils.createDataSource()
        Utils.deleteSchema(dataSource)
        Utils.createSchema(dataSource)
        productDao = ProductDaoImpl(dataSource)
        product = Product(ProductDaoImpl(dataSource), "product", Money.of(CurrencyUnit.USD, 100.0), LocalDate.parse("2021-06-27"))
    }

    @AfterTest
    fun tearDown() {
        Utils.deleteSchema(dataSource)
    }

    @Test
    fun `should find product by name`() {
        assertNull(productDao.findByName("product"))
        Utils.executeSQL(INSERT_PRODUCT_SQL, dataSource)
        val product = productDao.findByName("product")
        assertNotNull(product)
        assertEquals("product", product.name)
        assertEquals(Money.of(CurrencyUnit.USD, 100.0), product.price)
        assertEquals(LocalDate.parse("2021-06-27"), product.expirationDate)
    }

    @Test
    fun `should save product`() {
        productDao.save(product)
        resultSet(SELECT_PRODUCTS_SQL) {
            assertTrue(next())
            assertEquals(product.name, getString("name"))
            assertEquals(product.price, Money.of(CurrencyUnit.USD, getBigDecimal("price")))
            assertEquals(product.expirationDate, getDate("expiration_date").toLocalDate())
        }
        assertFailsWith<SQLException> { productDao.save(product) }
    }

    @Test
    fun `should update product`() {
        Utils.executeSQL(INSERT_PRODUCT_SQL, dataSource)
        product.price = Money.of(CurrencyUnit.USD, 99.0)
        productDao.update(product)
        resultSet(SELECT_PRODUCTS_SQL) {
            assertTrue(next())
            assertEquals(product.name, getString("name"))
            assertEquals(product.price, Money.of(CurrencyUnit.USD, getBigDecimal("price")))
            assertEquals(product.expirationDate, getDate("expiration_date").toLocalDate())
        }
    }

    private fun resultSet(sql: String, block: ResultSet.() -> Unit) {
        dataSource.connection.use { it.createStatement().use { statement -> block(statement.executeQuery(sql)) } }
    }
}