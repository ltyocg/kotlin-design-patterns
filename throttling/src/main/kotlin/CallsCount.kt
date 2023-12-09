import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

class CallsCount {
    private val logger = KotlinLogging.logger {}
    private val tenantCallsCount = ConcurrentHashMap<String, AtomicLong>()
    fun addTenant(tenantName: String) {
        tenantCallsCount.putIfAbsent(tenantName, AtomicLong(0))
    }

    fun incrementCount(tenantName: String) {
        tenantCallsCount[tenantName]!!.incrementAndGet()
    }

    fun getCount(tenantName: String): Long = tenantCallsCount[tenantName]!!.get()
    fun reset() {
        tenantCallsCount.replaceAll { _, _ -> AtomicLong(0) }
        logger.info { "reset counters" }
    }
}
