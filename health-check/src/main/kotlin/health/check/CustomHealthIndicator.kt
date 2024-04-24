package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class CustomHealthIndicator(
    private val healthChecker: AsynchronousHealthChecker,
    private val cacheManager: CacheManager,
    private val healthCheckRepository: HealthCheckRepository,
    @Value("\${health.check.timeout:10}")
    private val timeoutInSeconds: Long = 10
) : HealthIndicator {
    private val logger = KotlinLogging.logger {}

    @Cacheable(value = ["health-check"], unless = "#result.status == 'DOWN'")
    override fun health(): Health {
        logger.info { "Performing health check" }
        try {
            return healthChecker.performCheck({
                val databaseIsUp = healthCheckRepository.checkHealth() == 1
                logger.info { "Health check result: $databaseIsUp" }
                if (databaseIsUp) Health.up()
                    .withDetail("database", "reachable")
                    .build()
                else Health.down()
                    .withDetail("database", "unreachable")
                    .build()
            }, timeoutInSeconds)[timeoutInSeconds, TimeUnit.SECONDS]
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
            logger.error(e) { "Health check interrupted" }
            throw HealthCheckInterruptedException(e)
        } catch (e: Exception) {
            logger.error(e) { "Health check failed" }
            return Health.down(e).build()
        }
    }

    @Scheduled(fixedRateString = "\${health.check.cache.evict.interval:60000}")
    fun evictHealthCache() {
        logger.info { "Evicting health check cache" }
        try {
            val healthCheckCache = cacheManager.getCache("health-check")
            logger.info { "Health check cache: $healthCheckCache" }
            healthCheckCache?.clear()
        } catch (e: Exception) {
            logger.error(e) { "Failed to evict health check cache" }
        }
    }
}
