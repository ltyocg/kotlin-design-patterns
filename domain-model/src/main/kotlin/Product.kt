import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.RoundingMode
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Product(
    private val productDao: ProductDao,
    val name: String,
    var price: Money,
    var expirationDate: LocalDate
) {
    fun save() {
        if (productDao.findByName(name) != null) productDao.update(this)
        else productDao.save(this)
    }

    val salePrice: Money
        get() = price.minus(
            if (ChronoUnit.DAYS.between(LocalDate.now(), expirationDate) < DAYS_UNTIL_EXPIRATION_WHEN_DISCOUNT_ACTIVE) price.multipliedBy(DISCOUNT_RATE, RoundingMode.DOWN)
            else Money.zero(CurrencyUnit.USD)
        )

    companion object {
        const val DAYS_UNTIL_EXPIRATION_WHEN_DISCOUNT_ACTIVE = 4
        private const val DISCOUNT_RATE = 0.2
    }
}