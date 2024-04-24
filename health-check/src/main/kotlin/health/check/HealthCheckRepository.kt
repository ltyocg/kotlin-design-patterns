package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
class HealthCheckRepository {
    private val logger = KotlinLogging.logger {}

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    fun checkHealth(): Int = try {
        entityManager.createNativeQuery("SELECT 1").singleResult as Int
    } catch (e: Exception) {
        logger.error(e) { "Health check query failed" }
        throw e
    }

    @Transactional
    fun performTestTransaction() = try {
        val healthCheck = HealthCheck(status = "OK")
        entityManager.persist(healthCheck)
        entityManager.flush()
        entityManager.remove(entityManager.find(HealthCheck::class.java, healthCheck.id))
    } catch (e: Exception) {
        logger.error(e) { "Test transaction failed" }
        throw e
    }
}
