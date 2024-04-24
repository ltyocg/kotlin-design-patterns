package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component
import java.lang.management.ManagementFactory
import java.time.Instant

@Component
class CpuHealthIndicator(
    @Value("\${cpu.system.load.threshold:80.0}")
    private val systemCpuLoadThreshold: Double,
    @Value("\${cpu.process.load.threshold:50.0}")
    private val processCpuLoadThreshold: Double,
    @Value("\${cpu.load.average.threshold:0.75}")
    private val loadAverageThreshold: Double
) : HealthIndicator {
    private val logger = KotlinLogging.logger {}
    private val osBean = ManagementFactory.getOperatingSystemMXBean()

    override fun health(): Health {
        if (osBean !is com.sun.management.OperatingSystemMXBean) {
            logger.error { "Unsupported operating system MXBean: ${osBean.javaClass.name}" }
            return Health.unknown()
                .withDetail(ERROR_MESSAGE, "Unsupported operating system MXBean")
                .build()
        }
        val systemCpuLoad = osBean.cpuLoad * 100
        val processCpuLoad = osBean.processCpuLoad * 100
        val availableProcessors = osBean.availableProcessors
        val loadAverage = osBean.systemLoadAverage
        val details = buildMap {
            put("timestamp", Instant.now())
            put("systemCpuLoad", "%.2f%%".format(systemCpuLoad))
            put("processCpuLoad", "%.2f%%".format(processCpuLoad))
            put("availableProcessors", "%.2f%%".format(availableProcessors))
            put("loadAverage", loadAverage)
        }
        return when {
            systemCpuLoad > systemCpuLoadThreshold -> {
                logger.error { "High system CPU load: $systemCpuLoad" }
                Health.down()
                    .withDetails(details)
                    .withDetail(ERROR_MESSAGE, "High system CPU load")
                    .build()
            }

            processCpuLoad > processCpuLoadThreshold -> {
                logger.error { "High process CPU load: $processCpuLoad" }
                Health.down()
                    .withDetails(details)
                    .withDetail(ERROR_MESSAGE, "High process CPU load")
                    .build()
            }

            loadAverage > (availableProcessors * loadAverageThreshold) -> {
                logger.error { "High load average: $loadAverage" }
                Health.up()
                    .withDetails(details)
                    .withDetail(ERROR_MESSAGE, "High load average")
                    .build()
            }

            else -> Health.up().withDetails(details).build()
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "error"
    }
}
