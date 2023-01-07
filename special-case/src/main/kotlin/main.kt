import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
private const val TEST_USER_1 = "ignite1771"
private const val TEST_USER_2 = "abc123"
private const val ITEM_TV = "tv"
private const val ITEM_CAR = "car"
private const val ITEM_COMPUTER = "computer"
fun main() {
    log.info("Db seeding: 1 user: {\"ignite1771\", amount = 1000.0}, 2 products: {\"computer\": price = 800.0, \"car\": price = 20000.0}")
    Db.seedUser(TEST_USER_1, 1000.0)
    Db.seedItem(ITEM_COMPUTER, 800.0)
    Db.seedItem(ITEM_CAR, 20000.0)
    log.requestInfo(TEST_USER_2, ITEM_TV)
    ApplicationServices.loggedInUserPurchase("abc123", ITEM_TV).show()
    MaintenanceLock.lock = false
    log.requestInfo(TEST_USER_2, ITEM_TV)
    ApplicationServices.loggedInUserPurchase("abc123", ITEM_TV).show()
    log.requestInfo(TEST_USER_1, ITEM_TV)
    ApplicationServices.loggedInUserPurchase("ignite1771", ITEM_TV).show()
    log.requestInfo(TEST_USER_1, ITEM_CAR)
    ApplicationServices.loggedInUserPurchase("ignite1771", ITEM_CAR).show()
    log.requestInfo(TEST_USER_1, ITEM_COMPUTER)
    ApplicationServices.loggedInUserPurchase("ignite1771", ITEM_COMPUTER).show()
}

private fun Logger.requestInfo(user: String, item: String) = info("[REQUEST] User: {} buy product: {}", user, item)