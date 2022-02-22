import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.LocalDate
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductTest {
    private lateinit var productDao: ProductDao
    private lateinit var product: Product

    @BeforeTest
    fun setUp() {
        productDao = mock()
        product = Product(productDao, "product", Money.of(CurrencyUnit.USD, 100.0), LocalDate.now().plusDays(10))
    }

    @Test
    fun `should save product`() {
        whenever(productDao.findByName("product")).thenReturn(null)
        product.save()
        verify(productDao, times(1)).save(product)
        whenever(productDao.findByName("product")).thenReturn(product)
        product.save()
        verify(productDao, times(1)).update(product)
    }

    @Test
    fun `should get salePrice of product`() {
        assertEquals(Money.of(CurrencyUnit.USD, 100.0), product.salePrice)
        product.expirationDate = LocalDate.now().plusDays(2)
        assertEquals(Money.of(CurrencyUnit.USD, 80.0), product.salePrice)
    }
}