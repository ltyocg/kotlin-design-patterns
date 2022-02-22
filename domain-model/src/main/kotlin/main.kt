import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.time.LocalDate

fun main() {
    val dataSource = Utils.createDataSource()
    Utils.deleteSchema(dataSource)
    Utils.createSchema(dataSource)
    val customerDao = CustomerDaoImpl(dataSource)
    val tom = Customer(customerDao, "Tom", Money.of(CurrencyUnit.USD, 30.0)).apply { save() }
    val productDao = ProductDaoImpl(dataSource)
    val eggs = Product(productDao, "Eggs", Money.of(CurrencyUnit.USD, 10.0), LocalDate.now().plusDays(7)).apply { save() }
    val butter = Product(productDao, "Butter", Money.of(CurrencyUnit.USD, 20.0), LocalDate.now().plusDays(9)).apply { save() }
    val cheese = Product(productDao, "Cheese", Money.of(CurrencyUnit.USD, 25.0), LocalDate.now().plusDays(2)).apply { save() }
    with(tom) {
        showBalance()
        showPurchases()
        buyProduct(eggs)
        showBalance()
        buyProduct(butter)
        showBalance()
        buyProduct(cheese)
        showBalance()
        returnProduct(butter)
        showBalance()
        buyProduct(cheese)
        save()
        showBalance()
        showPurchases()
    }
}
