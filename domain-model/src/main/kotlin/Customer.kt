import io.github.oshai.kotlinlogging.KotlinLogging
import org.joda.money.Money
import java.sql.SQLException

class Customer(
    private val customerDao: CustomerDao,
    val name: String,
    var money: Money
) {
    private val logger = KotlinLogging.logger {}
    var purchases = mutableListOf<Product>()
    fun save() =
        if (customerDao.findByName(name) != null) customerDao.update(this)
        else customerDao.save(this)

    fun buyProduct(product: Product) {
        logger.info { "$name want to buy ${product.name}($%.2f)...".format(product.salePrice.amount) }
        if (money < product.salePrice) {
            logger.error { "Not enough money!" }
            return
        }
        try {
            money = money.minus(product.salePrice)
            customerDao.addProduct(product, this)
            purchases.add(product)
            logger.info { "$name bought ${product.name}!" }
        } catch (e: SQLException) {
            receiveMoney(product.salePrice)
            logger.error { e.localizedMessage }
        }
    }

    fun returnProduct(product: Product) {
        logger.info { "$name want to return ${product.name}($%.2f)...".format(product.salePrice.amount) }
        if (product in purchases) try {
            customerDao.deleteProduct(product, this)
            purchases.remove(product)
            receiveMoney(product.salePrice)
            logger.info { "$name returned ${product.name}!" }
        } catch (e: SQLException) {
            logger.error { e.localizedMessage }
        } else logger.error { "$name didn't buy ${product.name}..." }
    }

    fun showPurchases() =
        if (purchases.isEmpty()) logger.info { "$name didn't bought anything" }
        else logger.info { "$name bought: ${purchases.joinToString { "${it.name} - \$${it.salePrice.amount}" }}" }

    fun showBalance() = logger.info { "$name balance $money" }
    private fun receiveMoney(amount: Money) {
        money = money.plus(amount)
    }
}
