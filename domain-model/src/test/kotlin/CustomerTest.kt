import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CustomerTest {
    private lateinit var customerDao: CustomerDao
    private lateinit var customer: Customer
    private lateinit var product: Product

    @BeforeTest
    fun setUp() {
        customerDao = mock()
        customer = Customer(customerDao, "customer", Money.of(CurrencyUnit.USD, 100.0))
        product = Product(mock(), "product", Money.of(CurrencyUnit.USD, 100.0), LocalDate.now().plusDays(Product.DAYS_UNTIL_EXPIRATION_WHEN_DISCOUNT_ACTIVE.toLong()))
    }

    @Test
    fun `should save customer`() {
        whenever(customerDao.findByName("customer")).thenReturn(null)
        customer.save()
        verify(customerDao, times(1)).save(customer)
        whenever(customerDao.findByName("customer")).thenReturn(customer)
        customer.save()
        verify(customerDao, times(1)).update(customer)
    }

    @Test
    fun `should add product to purchases`() {
        product.price = Money.of(CurrencyUnit.USD, 200.0)
        customer.buyProduct(product)
        assertContentEquals(emptyList(), customer.purchases)
        assertEquals(Money.of(CurrencyUnit.USD, 100.0), customer.money)
        product.price = Money.of(CurrencyUnit.USD, 100.0)
        customer.buyProduct(product)
        assertContentEquals(listOf(product), customer.purchases)
        assertEquals(Money.zero(CurrencyUnit.USD), customer.money)
    }

    @Test
    fun `should remove product from purchases`() {
        customer.purchases = mutableListOf(product)
        customer.returnProduct(product)
        assertContentEquals(emptyList(), customer.purchases)
        assertEquals(Money.of(CurrencyUnit.USD, 200.0), customer.money)
        customer.returnProduct(product)
        assertContentEquals(emptyList(), customer.purchases)
        assertEquals(Money.of(CurrencyUnit.USD, 200.0), customer.money)
    }
}