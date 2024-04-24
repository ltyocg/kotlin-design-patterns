package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component
import java.lang.management.ManagementFactory
import java.util.concurrent.ExecutionException

@Component
class MemoryHealthIndicator(
    private val asynchronousHealthChecker: AsynchronousHealthChecker,
    @Value("\${health.check.timeout:10}")
    private val timeoutInSeconds: Long,
    @Value("\${health.check.memory.threshold:0.9}")
    private val memoryThreshold: Double
) : HealthIndicator {
    private val logger = KotlinLogging.logger {}
    fun checkMemory(): Health = try {
        val future = asynchronousHealthChecker.performCheck({
            val heapMemoryUsage = ManagementFactory.getMemoryMXBean().heapMemoryUsage
            val memoryUsage = heapMemoryUsage.used.toDouble() / heapMemoryUsage.max
            val format = "%.2f%% of %d max".format(memoryUsage * 100, heapMemoryUsage.max)
            if (memoryUsage < memoryThreshold) {
                logger.info { "Memory usage is below threshold: $format" }
                Health.up()
                    .withDetail("memory usage", format)
                    .build()
            } else Health.down()
                .withDetail("memory usage", format)
                .build()
        }, timeoutInSeconds)
        future.get()
    } catch (e: InterruptedException) {
        logger.error(e) { "Health check interrupted" }
        Thread.currentThread().interrupt()
        Health.down()
            .withDetail("error", "Health check interrupted")
            .build()
    } catch (e: ExecutionException) {
        logger.error(e) { "Health check failed" }
        val cause = if (e.cause == null) e else e.cause
        Health.down()
            .withDetail("error", cause.toString())
            .build()
    }

    override fun health(): Health = checkMemory()
}
