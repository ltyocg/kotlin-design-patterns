import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class ServiceAmbassadorTest {
    @Test
    fun test() = runBlocking {
        assertTrue(ServiceAmbassador.doRemoteFunction(10).let { it == 100L || it == RemoteServiceStatus.FAILURE.remoteServiceStatusValue })
    }
}