import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info("Db seeding: 1 user: {\"ignite1771\", amount = 1000.0}, 2 products: {\"computer\": price = 800.0, \"car\": price = 20000.0}")
    Db.seedUser("ignite1771", 1000.0)
    Db.seedItem("computer", 800.0)
    Db.seedItem("car", 20000.0)
    log.info("[REQUEST] User: abc123 buy product: tv")
    ApplicationServices.loggedInUserPurchase("abc123", "tv").show()
    MaintenanceLock.lock = false
    log.info("[REQUEST] User: abc123 buy product: tv")
    ApplicationServices.loggedInUserPurchase("abc123", "tv").show()
    log.info("[REQUEST] User: ignite1771 buy product: tv")
    ApplicationServices.loggedInUserPurchase("ignite1771", "tv").show()
    log.info("[REQUEST] User: ignite1771 buy product: car")
    ApplicationServices.loggedInUserPurchase("ignite1771", "car").show()
    log.info("[REQUEST] User: ignite1771 buy product: computer")
    ApplicationServices.loggedInUserPurchase("ignite1771", "computer").show()
}