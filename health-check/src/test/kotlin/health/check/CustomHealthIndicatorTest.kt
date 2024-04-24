package health.check

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.Status
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.CompletableFuture
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomHealthIndicatorTest {
    @Mock
    private lateinit var healthChecker: AsynchronousHealthChecker

    @Mock
    private lateinit var cacheManager: CacheManager

    @Mock
    private lateinit var healthCheckRepository: HealthCheckRepository

    @Mock
    private lateinit var cache: Cache
    private lateinit var customHealthIndicator: CustomHealthIndicator

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        whenever(cacheManager.getCache("health-check")).thenReturn(cache)
        customHealthIndicator = CustomHealthIndicator(healthChecker, cacheManager, healthCheckRepository)
    }

    @Test
    fun `when database is up, then health is up`() {
        whenever(healthChecker.performCheck(any(), any())).thenReturn(
            CompletableFuture.completedFuture(
                Health.up()
                    .withDetail("database", "reachable")
                    .build()
            )
        )
        whenever(healthCheckRepository.checkHealth()).thenReturn(1)
        assertEquals(Status.UP, customHealthIndicator.health().status)
    }

    @Test
    fun `when database is down, then health is down`() {
        whenever(healthChecker.performCheck(any(), any())).thenReturn(
            CompletableFuture.completedFuture(
                Health
                    .down()
                    .withDetail("database", "unreachable")
                    .build()
            )
        )
        whenever(healthCheckRepository.checkHealth()).thenReturn(null)
        assertEquals(Status.DOWN, customHealthIndicator.health().status)
    }

    @Test
    fun `when health check times out, then health is down`() {
        whenever(healthChecker.performCheck(any(), any())).thenReturn(CompletableFuture())
        assertEquals(Status.DOWN, customHealthIndicator.health().status)
    }

    @Test
    fun `when evict health cache, then cache is cleared`() {
        doNothing().whenever(cache).clear()
        customHealthIndicator.evictHealthCache()
        verify(cache, Mockito.times(1)).clear()
        verify(cacheManager, Mockito.times(1)).getCache("health-check")
    }

    @Configuration
    class CacheConfig {
        @Bean
        fun cacheManager(): CacheManager = ConcurrentMapCacheManager("health-check")
    }
}
