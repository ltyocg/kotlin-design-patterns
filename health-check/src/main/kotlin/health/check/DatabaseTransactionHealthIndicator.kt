package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutionException

@Component
class DatabaseTransactionHealthIndicator(
    private val healthCheckRepository: HealthCheckRepository,
    private val asynchronousHealthChecker: AsynchronousHealthChecker,
    @Value("\${health.check.timeout:10}")
    private val timeoutInSeconds: Long
) : HealthIndicator {
    private val logger = KotlinLogging.logger {}
    override fun health(): Health {
        logger.info { "Calling performCheck with timeout $timeoutInSeconds" }
        try {
            return asynchronousHealthChecker.performCheck({
                try {
                    healthCheckRepository.performTestTransaction()
                    Health.up().build()
                } catch (e: Exception) {
                    logger.error(e) { "Database transaction health check failed" }
                    Health.down(e).build()
                }
            }, timeoutInSeconds).get()
        } catch (e: InterruptedException) {
            logger.error(e) { "Database transaction health check timed out or was interrupted" }
            Thread.currentThread().interrupt()
            return Health.down(e).build()
        } catch (e: ExecutionException) {
            logger.error(e) { "Database transaction health check timed out or was interrupted" }
            Thread.currentThread().interrupt()
            return Health.down(e).build()
        }
    }
}
