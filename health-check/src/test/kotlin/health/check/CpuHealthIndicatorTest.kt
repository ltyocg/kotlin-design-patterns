package health.check

import com.sun.management.OperatingSystemMXBean
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.boot.actuate.health.Status
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CpuHealthIndicatorTest {
    private lateinit var cpuHealthIndicator: CpuHealthIndicator
    private lateinit var mockOsBean: OperatingSystemMXBean

    @BeforeTest
    fun setUp() {
        mockOsBean = mock()
        cpuHealthIndicator = CpuHealthIndicator(80.0, 50.0, 0.75)
        try {
            val osBeanField = CpuHealthIndicator::class.java.getDeclaredField("osBean")
            osBeanField.isAccessible = true
            osBeanField[cpuHealthIndicator] = mockOsBean
        } catch (e: NoSuchFieldException) {
            throw RuntimeException(e)
        } catch (e: IllegalAccessException) {
            throw RuntimeException(e)
        }
    }

    @Test
    fun `when system cpu load is high, then health is down`() {
        whenever(mockOsBean.cpuLoad).thenReturn(0.9)
        whenever(mockOsBean.availableProcessors).thenReturn(8)
        whenever(mockOsBean.systemLoadAverage).thenReturn(9.0)
        val health = cpuHealthIndicator.health()
        assertEquals(
            Status.DOWN,
            health.status,
            "Health status should be DOWN when system CPU load is high"
        )
        assertEquals(
            "High system CPU load",
            health.details["error"],
            "Error message should indicate high system CPU load"
        )
    }

    @Test
    fun `when process cpu load is high, then health is down`() {
        whenever(mockOsBean.cpuLoad).thenReturn(0.5)
        whenever(mockOsBean.processCpuLoad).thenReturn(0.8)
        whenever(mockOsBean.availableProcessors).thenReturn(8)
        whenever(mockOsBean.systemLoadAverage).thenReturn(5.0)
        val health = cpuHealthIndicator.health()
        assertEquals(
            Status.DOWN,
            health.status,
            "Health status should be DOWN when process CPU load is high"
        )
        assertEquals(
            "High process CPU load",
            health.details["error"],
            "Error message should indicate high process CPU load"
        )
    }
}
