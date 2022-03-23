import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class LoadBalancerTest {
    @Test
    fun `same state amongst all instances`() {
        val firstBalancer = LoadBalancer()
        val secondBalancer = LoadBalancer()
        firstBalancer.addServer(Server("localhost", 8085, 6))
        assertEquals(firstBalancer.noOfServers, secondBalancer.noOfServers)
        assertEquals(firstBalancer.lastServedId, secondBalancer.lastServedId)
    }

    @Test
    fun serve() {
        val server = mock<Server>()
        whenever(server.host).thenReturn("testhost")
        whenever(server.port).thenReturn(1234)
        doNothing().whenever(server).serve(any())
        val loadBalancer = LoadBalancer()
        loadBalancer.addServer(server)
        verifyNoMoreInteractions(server)
        val request = Request("test")
        repeat(loadBalancer.noOfServers * 2) { loadBalancer.serverRequest(request) }
        verify(server, times(2)).serve(request)
        verifyNoMoreInteractions(server)
    }
}