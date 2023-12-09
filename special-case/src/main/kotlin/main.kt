import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
private const val TEST_USER_1 = "ignite1771"
private const val TEST_USER_2 = "abc123"
private const val ITEM_TV = "tv"
private const val ITEM_CAR = "car"
private const val ITEM_COMPUTER = "computer"
fun main() {
    logger.info { "Db seeding: 1 user: {\"ignite1771\", amount = 1000.0}, 2 products: {\"computer\": price = 800.0, \"car\": price = 20000.0}" }
    Db.seedUser(TEST_USER_1, 1000.0)
    Db.seedItem(ITEM_COMPUTER, 800.0)
    Db.seedItem(ITEM_CAR, 20000.0)
    logger.requestInfo(TEST_USER_2, ITEM_TV)
    ApplicationServices.loggedInUserPurchase("abc123", ITEM_TV).show()
    MaintenanceLock.lock = false
    logger.requestInfo(TEST_USER_2, ITEM_TV)
    ApplicationServices.loggedInUserPurchase("abc123", ITEM_TV).show()
    logger.requestInfo(TEST_USER_1, ITEM_TV)
    ApplicationServices.loggedInUserPurchase("ignite1771", ITEM_TV).show()
    logger.requestInfo(TEST_USER_1, ITEM_CAR)
    ApplicationServices.loggedInUserPurchase("ignite1771", ITEM_CAR).show()
    logger.requestInfo(TEST_USER_1, ITEM_COMPUTER)
    ApplicationServices.loggedInUserPurchase("ignite1771", ITEM_COMPUTER).show()
}

private fun KLogger.requestInfo(user: String, item: String) = info { "[REQUEST] User: $user buy product: $item" }
