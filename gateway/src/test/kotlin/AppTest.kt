import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    private lateinit var executorService: ExecutorService

    @BeforeTest
    fun setUp() {
        executorService = Executors.newFixedThreadPool(2)
        GatewayFactory.registerGateway("ServiceA", ExternalServiceA())
        GatewayFactory.registerGateway("ServiceB", ExternalServiceB())
        GatewayFactory.registerGateway("ServiceC", ExternalServiceC())
    }

    @Test
    fun `ServiceA execution`() {
        executorService.submit(GatewayFactory.getGateway("ServiceA")!!::execute).get()
    }

    @Test
    fun `ServiceB execution`() {
        executorService.submit(GatewayFactory.getGateway("ServiceB")!!::execute).get()
    }

    @Test
    fun `ServiceC execution`() {
        executorService.submit(GatewayFactory.getGateway("ServiceC")!!::execute).get()
    }

    @Test
    fun `ServiceC error`() = try {
        (GatewayFactory.getGateway("ServiceC") as ExternalServiceC?)!!.error()
    } catch (e: Exception) {
        assertEquals("Service C encountered an error", e.message)
    }
}
