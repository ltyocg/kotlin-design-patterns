import io.github.oshai.kotlinlogging.KotlinLogging

sealed interface ReceiptViewModel {
    fun show()
}

class DownForMaintenance : ReceiptViewModel {
    private val logger = KotlinLogging.logger {}
    override fun show() = logger.info { "Down for maintenance" }
}

class InsufficientFunds(
    private val userName: String,
    private val amount: Double,
    private val itemName: String?
) : ReceiptViewModel {
    private val logger = KotlinLogging.logger {}
    override fun show() = logger.info { "Insufficient funds: $amount of user: $userName for buying item: $itemName" }
}

class InvalidUser(private val userName: String?) : ReceiptViewModel {
    private val logger = KotlinLogging.logger {}
    override fun show() = logger.info { "Invalid user: $userName" }
}

class OutOfStock(private val userName: String, private val itemName: String?) : ReceiptViewModel {
    private val logger = KotlinLogging.logger {}
    override fun show() = logger.info { "Out of stock: $itemName for user = $userName to buy" }
}

class ReceiptDto(val price: Double) : ReceiptViewModel {
    private val logger = KotlinLogging.logger {}
    override fun show() = logger.info { "Receipt: $price paid" }
}
