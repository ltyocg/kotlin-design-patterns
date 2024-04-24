package health.check

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever
import org.springframework.boot.actuate.health.Status
import java.lang.management.GarbageCollectorMXBean
import java.lang.management.MemoryPoolMXBean
import java.lang.management.MemoryUsage
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GarbageCollectionHealthIndicatorTest {
    @Mock
    private lateinit var garbageCollectorMXBean: GarbageCollectorMXBean

    @Mock
    private lateinit var memoryPoolMXBean: MemoryPoolMXBean
    private lateinit var healthIndicator: GarbageCollectionHealthIndicator

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        healthIndicator = spy(object : GarbageCollectionHealthIndicator(0.8) {
            override val garbageCollectorMxBeans: List<GarbageCollectorMXBean>
                get() = listOf(garbageCollectorMXBean)
            override val memoryPoolMxBeans: List<MemoryPoolMXBean>
                get() = listOf(memoryPoolMXBean)
        })
    }

    @Test
    fun `when memory usage is low, then health is up`() {
        whenever(garbageCollectorMXBean.collectionCount).thenReturn(100L)
        whenever(garbageCollectorMXBean.collectionTime).thenReturn(1000L)
        whenever(garbageCollectorMXBean.memoryPoolNames).thenReturn(arrayOf("Eden Space"))
        whenever(memoryPoolMXBean.usage).thenReturn(MemoryUsage(0, 100, 500, 1000))
        whenever(memoryPoolMXBean.name).thenReturn("Eden Space")
        assertEquals(Status.UP, healthIndicator.health().status)
    }

    @Test
    fun `when memory usage is high, then health contains warning`() {
        val threshold = 0.8
        val poolName = "CodeCache"
        whenever(garbageCollectorMXBean.name).thenReturn("G1 Young Generation")
        whenever(garbageCollectorMXBean.memoryPoolNames).thenReturn(arrayOf(poolName))
        val maxMemory = 1000L
        val usedMemory = (threshold * maxMemory).toLong() + 1
        whenever(memoryPoolMXBean.usage).thenReturn(MemoryUsage(0, usedMemory, usedMemory, maxMemory))
        whenever(memoryPoolMXBean.name).thenReturn(poolName)
        val gcDetails = healthIndicator.health().details["G1 Young Generation"] as Map<*, *>?
        assertNotNull(gcDetails, "Expected details for 'G1 Young Generation', but none were found.")
        val memoryPoolsDetail = gcDetails["memoryPools"] as String?
        assertNotNull(memoryPoolsDetail, "Expected memory pool details for 'CodeCache', but none were found.")
        val memoryUsageReported = memoryPoolsDetail.split(Regex(": "))
            .dropLastWhile { it.isEmpty() }
            .first()
            .trim { it <= ' ' }
            .replace("%", "")
        assertTrue(memoryUsageReported.toDouble() > threshold, "Memory usage percentage should be above the threshold.")
        val warning = gcDetails["warning"] as String?
        assertNotNull(warning, "Expected a warning for high memory usage, but none was found.")
        assertTrue(warning.matches(Regex("Memory pool '$poolName' usage is high \\(\\d+\\.\\d+%%\\)")), "Expected a high usage warning, but format is incorrect: $warning")
    }

    @Test
    fun `when no garbage collections, then healthIsUp`() {
        whenever(garbageCollectorMXBean.collectionCount).thenReturn(0L)
        whenever(garbageCollectorMXBean.collectionTime).thenReturn(0L)
        whenever(garbageCollectorMXBean.name).thenReturn("G1 Young Generation")
        whenever(garbageCollectorMXBean.memoryPoolNames).thenReturn(arrayOf())
        val health = healthIndicator.health()
        assertEquals(Status.UP, health.status)
        val gcDetails = health.details["G1 Young Generation"] as Map<*, *>?
        assertNotNull(gcDetails, "Expected details for 'G1 Young Generation', but none were found.")
        assertNull(gcDetails["warning"], "Expected no warning for 'G1 Young Generation' as there are no collections.")
    }
}
