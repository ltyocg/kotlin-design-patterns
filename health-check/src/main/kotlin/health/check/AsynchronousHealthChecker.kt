package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PreDestroy
import org.springframework.boot.actuate.health.Health
import org.springframework.stereotype.Component
import java.util.concurrent.*

@Component
class AsynchronousHealthChecker {
    private val logger = KotlinLogging.logger {}
    private val healthCheckExecutor = Executors.newSingleThreadScheduledExecutor()
    fun performCheck(healthCheck: () -> Health, timeoutInSeconds: Long): CompletableFuture<Health> {
        val future = CompletableFuture.supplyAsync(healthCheck, healthCheckExecutor)
        healthCheckExecutor.schedule(
            {
                if (!future.isDone) {
                    logger.error { HEALTH_CHECK_TIMEOUT_MESSAGE }
                    future.completeExceptionally(TimeoutException(HEALTH_CHECK_TIMEOUT_MESSAGE))
                }
            },
            timeoutInSeconds,
            TimeUnit.SECONDS
        )
        return future.handle { result: Health?, throwable: Throwable? ->
            if (throwable != null) {
                logger.error(throwable) { HEALTH_CHECK_FAILED_MESSAGE }
                val rootCause = if (throwable is CompletionException) throwable.cause else throwable
                if (rootCause is TimeoutException) {
                    logger.error(rootCause) { HEALTH_CHECK_TIMEOUT_MESSAGE }
                    throw CompletionException(rootCause)
                } else {
                    logger.error(rootCause) { HEALTH_CHECK_FAILED_MESSAGE }
                    Health.down().withException(rootCause).build()
                }
            } else result
        }
    }

    private fun awaitTerminationWithTimeout(): Boolean =
        (!healthCheckExecutor.awaitTermination(5, TimeUnit.SECONDS))
            .also { logger.info { "Termination status: $it" } }

    @PreDestroy
    fun shutdown() {
        try {
            if (awaitTerminationWithTimeout()) {
                logger.info { "Health check executor did not terminate in time" }
                healthCheckExecutor.shutdownNow()
                if (awaitTerminationWithTimeout()) logger.error { "Health check executor did not terminate" }
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
            healthCheckExecutor.shutdownNow()
            logger.error(e) { "Shutdown of the health check executor was interrupted" }
        }
    }

    companion object {
        private const val HEALTH_CHECK_TIMEOUT_MESSAGE = "Health check timed out"
        private const val HEALTH_CHECK_FAILED_MESSAGE = "Health check failed"
    }
}
