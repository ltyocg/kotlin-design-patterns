package health.check

import jakarta.persistence.EntityManager
import jakarta.persistence.Query
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
class HealthCheckRepositoryTest {
    @Mock
    private lateinit var entityManager: EntityManager

    @InjectMocks
    private lateinit var healthCheckRepository: HealthCheckRepository

    @Test
    fun `when check health, then returns one`() {
        val mockedQuery = mock<Query>()
        whenever(entityManager.createNativeQuery("SELECT 1")).thenReturn(mockedQuery)
        whenever(mockedQuery.singleResult).thenReturn(1)
        val healthCheckResult = healthCheckRepository.checkHealth()
        assertNotNull(healthCheckResult)
        assertEquals(1, healthCheckResult)
    }

    @Test
    fun `when perform test transaction, then succeeds`() {
        whenever(entityManager.find(eq(HealthCheck::class.java), any())).thenReturn(HealthCheck().apply { status = "OK" })
        healthCheckRepository.performTestTransaction()
        verify(entityManager).persist(any<HealthCheck>())
        verify(entityManager).flush()
        verify(entityManager).remove(any<HealthCheck>())
    }

    @Test
    fun `when check health, and database is down, then throws exception`() {
        whenever(entityManager.createNativeQuery("SELECT 1")).thenThrow(RuntimeException::class.java)
        assertFailsWith<RuntimeException> { healthCheckRepository.checkHealth() }
    }

    @Test
    fun `when perform test transaction, and persist fails, then throws exception`() {
        doThrow(RuntimeException()).whenever(entityManager).persist(any<HealthCheck>())
        assertFailsWith<RuntimeException> { healthCheckRepository.performTestTransaction() }
        verify(entityManager, never()).remove(any<HealthCheck>())
    }
}
