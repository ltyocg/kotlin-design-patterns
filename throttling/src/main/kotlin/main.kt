import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}
fun main() {
    val callsCount = CallsCount()
    val executorService = Executors.newFixedThreadPool(2)
    executorService.execute { makeServiceCalls(BarCustomer("young human", 2, callsCount), callsCount) }
    executorService.execute { makeServiceCalls(BarCustomer("dwarf soldier", 4, callsCount), callsCount) }
    executorService.shutdown()
    try {
        if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) executorService.shutdownNow()
    } catch (e: InterruptedException) {
        executorService.shutdownNow()
    }
}

private fun makeServiceCalls(barCustomer: BarCustomer, callsCount: CallsCount) {
    val service = Bartender(ThrottleTimer(1000, callsCount), callsCount)
    repeat(50) {
        service.orderDrink(barCustomer)
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            logger.error { "Thread interrupted: ${e.localizedMessage}" }
        }
    }
}
