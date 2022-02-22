import java.util.*
import kotlin.test.*

class PropertiesFeatureToggleVersionTest {
    @Test
    fun `test non boolean property`() {
        assertFailsWith<IllegalArgumentException> { PropertiesFeatureToggleVersion(Properties().apply { put("enhancedWelcome", "Something") }) }
    }

    @Test
    fun `test feature turned on`() {
        val service = PropertiesFeatureToggleVersion(Properties().apply { put("enhancedWelcome", true) })
        assertTrue(service.enhanced)
        assertEquals("Welcome Jamie No Code. You're using the enhanced welcome message.", service.getWelcomeMessage(User("Jamie No Code")))
    }

    @Test
    fun `test feature turned off`() {
        val service = PropertiesFeatureToggleVersion(Properties().apply { put("enhancedWelcome", false) })
        assertFalse(service.enhanced)
        assertEquals("Welcome to the application.", service.getWelcomeMessage(User("Jamie No Code")))
    }
}