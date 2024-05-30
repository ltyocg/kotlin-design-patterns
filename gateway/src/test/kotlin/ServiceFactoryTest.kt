import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.test.*

class ServiceFactoryTest {
    private lateinit var executorService: ExecutorService

    @BeforeTest
    fun setUp() {
        executorService = Executors.newFixedThreadPool(2)
        GatewayFactory.registerGateway("ServiceA", ExternalServiceA())
        GatewayFactory.registerGateway("ServiceB", ExternalServiceB())
        GatewayFactory.registerGateway("ServiceC", ExternalServiceC())
    }

    @Test
    fun `GatewayFactory registration and retrieval`() {
        assertTrue(GatewayFactory.getGateway("ServiceA") is ExternalServiceA, "ServiceA should be an instance of ExternalServiceA")
        assertTrue(GatewayFactory.getGateway("ServiceB") is ExternalServiceB, "ServiceB should be an instance of ExternalServiceB")
        assertTrue(GatewayFactory.getGateway("ServiceC") is ExternalServiceC, "ServiceC should be an instance of ExternalServiceC")
    }

    @Test
    fun `GatewayFactory registration with non existing key`() = assertNull(GatewayFactory.getGateway("NonExistingService"))

    @Test
    fun `Gateway factory concurrency`() {
        val numThreads = 10
        val latch = CountDownLatch(numThreads)
        val failed = AtomicBoolean(false)
        repeat(numThreads) {
            executorService.submit {
                try {
                    GatewayFactory.getGateway("ServiceA")!!.execute()
                } catch (e: Exception) {
                    failed.set(true)
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await()
        assertFalse(failed.get(), "This should not fail")
    }
}
