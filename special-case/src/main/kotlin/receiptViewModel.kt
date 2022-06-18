import org.slf4j.LoggerFactory

sealed interface ReceiptViewModel {
    fun show()
}

class DownForMaintenance : ReceiptViewModel {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun show() = log.info("Down for maintenance")
}

class InsufficientFunds(
    private val userName: String,
    private val amount: Double,
    private val itemName: String?
) : ReceiptViewModel {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun show() = log.info("Insufficient funds: {} of user: {} for buying item: {}", amount, userName, itemName)
}

class InvalidUser(private val userName: String?) : ReceiptViewModel {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun show() = log.info("Invalid user: {}", userName)
}

class OutOfStock(private val userName: String, private val itemName: String?) : ReceiptViewModel {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun show() = log.info("Out of stock: {} for user = {} to buy", itemName, userName)
}

class ReceiptDto(val price: Double) : ReceiptViewModel {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun show() = log.info("Receipt: {} paid", price)
}