import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay

private val logger = KotlinLogging.logger {}
suspend fun main() {
    val delayedServiceCircuitBreaker = CircuitBreaker(DelayedRemoteService(System.nanoTime(), 5), 2, 2000L * 1000 * 1000)
    val quickServiceCircuitBreaker = CircuitBreaker(QuickRemoteService, 2, 2000L * 1000 * 1000)
    val monitoringService = MonitoringService(delayedServiceCircuitBreaker, quickServiceCircuitBreaker)
    logger.info { monitoringService.localResourceResponse() }
    logger.info { monitoringService.delayedServiceResponse() }
    logger.info { monitoringService.delayedServiceResponse() }
    logger.info { delayedServiceCircuitBreaker.state.name }
    logger.info { monitoringService.quickServiceResponse() }
    logger.info { quickServiceCircuitBreaker.state.name }
    logger.info { "Waiting for delayed service to become responsive" }
    delay(5000)
    logger.info { delayedServiceCircuitBreaker.state.name }
    logger.info { monitoringService.delayedServiceResponse() }
    logger.info { delayedServiceCircuitBreaker.state.name }
}
