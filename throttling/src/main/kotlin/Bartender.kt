import org.slf4j.LoggerFactory
import java.util.concurrent.ThreadLocalRandom

class Bartender(
    timer: () -> Unit,
    private val callsCount: CallsCount
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val randomCustomerId: Int
        get() = ThreadLocalRandom.current().nextInt(1, 10000)

    init {
        timer()
    }

    fun orderDrink(barCustomer: BarCustomer): Int {
        val tenantName = barCustomer.name
        val count = callsCount.getCount(tenantName)
        if (count >= barCustomer.allowedCallsPerSecond) {
            log.error("I'm sorry {}, you've had enough for today!", tenantName)
            return -1
        }
        callsCount.incrementCount(tenantName)
        log.debug("Serving beer to {} : [{} consumed] ", barCustomer.name, count + 1)
        return randomCustomerId
    }
}