import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ThreadLocalRandom

class Bartender(
    timer: () -> Unit,
    private val callsCount: CallsCount
) {
    private val logger = KotlinLogging.logger {}
    private val randomCustomerId: Int
        get() = ThreadLocalRandom.current().nextInt(1, 10000)

    init {
        timer()
    }

    fun orderDrink(barCustomer: BarCustomer): Int {
        val tenantName = barCustomer.name
        val count = callsCount.getCount(tenantName)
        if (count >= barCustomer.allowedCallsPerSecond) {
            logger.error { "I'm sorry $tenantName, you've had enough for today!" }
            return -1
        }
        callsCount.incrementCount(tenantName)
        logger.debug { "Serving beer to ${barCustomer.name} : [${count + 1} consumed] " }
        return randomCustomerId
    }
}
