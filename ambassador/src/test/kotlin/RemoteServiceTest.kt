import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class RemoteServiceTest {
    @Test
    fun `test failed call`() = runBlocking {
        RemoteService.randomProvider = { 0.21 }
        assertEquals(RemoteServiceStatus.FAILURE.remoteServiceStatusValue, RemoteService.doRemoteFunction(10))
    }

    @Test
    fun `test successful call`() = runBlocking {
        RemoteService.randomProvider = { 0.2 }
        assertEquals(100, RemoteService.doRemoteFunction(10))
    }
}