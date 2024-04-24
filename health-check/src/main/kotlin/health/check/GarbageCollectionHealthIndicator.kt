package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component
import java.lang.management.GarbageCollectorMXBean
import java.lang.management.ManagementFactory
import java.lang.management.MemoryPoolMXBean

@Component
class GarbageCollectionHealthIndicator(
    @Value("\${memory.usage.threshold:0.8}")
    private val memoryUsageThreshold: Double
) : HealthIndicator {
    private val logger = KotlinLogging.logger {}
    override fun health(): Health {
        val memoryPoolMxBeans = memoryPoolMxBeans
        return Health.up()
            .withDetails(garbageCollectorMxBeans.associateBy({ it.name }, { createCollectorDetails(it, memoryPoolMxBeans) }))
            .build()
    }

    private fun createCollectorDetails(
        gcBean: GarbageCollectorMXBean,
        memoryPoolMxBeans: List<MemoryPoolMXBean>
    ): Map<String, String> = buildMap {
        put("count", "%d".format(gcBean.collectionCount))
        put("time", "%dms".format(gcBean.collectionTime))
        val memoryPoolNamesList = gcBean.memoryPoolNames.toList()
        if (memoryPoolNamesList.isNotEmpty()) memoryPoolMxBeans.addMemoryPoolDetails(this, memoryPoolNamesList)
        else logger.error { "Garbage collector '${gcBean.name}' does not have any memory pools" }
    }

    private fun List<MemoryPoolMXBean>.addMemoryPoolDetails(
        collectorDetails: MutableMap<String, String>,
        memoryPoolNamesList: List<String>
    ) = forEach {
        if (it.name in memoryPoolNamesList) {
            val memoryUsage = it.usage.used / it.usage.max.toDouble()
            if (memoryUsage > memoryUsageThreshold)
                collectorDetails["warning"] = "Memory pool '%s' usage is high (%2f%%)".format(it.name, memoryUsage)
            collectorDetails["memoryPools"] = "%s: %s%%".format(it.name, memoryUsage)
        }
    }

    protected val garbageCollectorMxBeans: List<GarbageCollectorMXBean>
        get() = ManagementFactory.getGarbageCollectorMXBeans()
    protected val memoryPoolMxBeans: List<MemoryPoolMXBean>
        get() = ManagementFactory.getMemoryPoolMXBeans()
}
