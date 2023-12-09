import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val pool = OliphauntPool()
    logger.info { pool }
    fun checkedOutLog(arg: Any?) = logger.info { "Checked out $arg" }
    fun checkedInLog(arg: Any?) = logger.info { "Checked in $arg" }
    val oliphaunt1 = pool.checkOut()
    checkedOutLog(oliphaunt1)
    logger.info { pool }
    val oliphaunt2 = pool.checkOut()
    checkedOutLog(oliphaunt2)
    checkedOutLog(pool.checkOut())
    logger.info { pool }
    checkedInLog(oliphaunt1)
    pool.checkIn(oliphaunt1)
    checkedInLog(oliphaunt2)
    pool.checkIn(oliphaunt2)
    logger.info { pool }
    checkedOutLog(pool.checkOut())
    checkedOutLog(pool.checkOut())
    logger.info { pool }
}
