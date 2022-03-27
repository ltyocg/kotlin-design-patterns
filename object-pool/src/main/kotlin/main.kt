import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val pool = OliphauntPool()
    log.info(pool.toString())
    val oliphaunt1 = pool.checkOut()
    val checkedOut = "Checked out {}"
    log.info(checkedOut, oliphaunt1)
    log.info(pool.toString())
    val oliphaunt2 = pool.checkOut()
    log.info(checkedOut, oliphaunt2)
    val oliphaunt3 = pool.checkOut()
    log.info(checkedOut, oliphaunt3)
    log.info(pool.toString())
    log.info("Checking in {}", oliphaunt1)
    pool.checkIn(oliphaunt1)
    log.info("Checking in {}", oliphaunt2)
    pool.checkIn(oliphaunt2)
    log.info(pool.toString())
    val oliphaunt4 = pool.checkOut()
    log.info(checkedOut, oliphaunt4)
    val oliphaunt5 = pool.checkOut()
    log.info(checkedOut, oliphaunt5)
    log.info(pool.toString())
}