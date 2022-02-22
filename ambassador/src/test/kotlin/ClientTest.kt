import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class ClientTest {
    @Test
    fun test() = runBlocking {
        assertTrue(Client().useService(10).let { it == 100L || it == RemoteServiceStatus.FAILURE.remoteServiceStatusValue })
    }
}