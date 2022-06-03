import kotlin.test.*

class ServiceLocatorTest {
    @Test
    fun `get non existent service`() {
        assertNull(ServiceLocator.getService("fantastic/unicorn/service"))
        assertNull(ServiceLocator.getService("another/fantastic/unicorn/service"))
    }

    @Test
    fun serviceCache() = listOf("jndi/serviceA", "jndi/serviceB").forEach {
        val service = ServiceLocator.getService(it)
        assertNotNull(service)
        assertEquals(it, service.name)
        assertTrue(service.id > 0)
        assertSame(service, ServiceLocator.getService(it))
    }
}