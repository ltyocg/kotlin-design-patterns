import org.mockito.kotlin.mock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ClientPropertiesBeanTest {
    @Test
    fun `default constructor`() {
        val newBean = ClientPropertiesBean()
        assertEquals("DEFAULT_NAME", newBean.name)
        assertTrue(newBean.businessInterest)
        assertTrue(newBean.scienceNewsInterest)
        assertTrue(newBean.sportsInterest)
        assertTrue(newBean.worldNewsInterest)
    }

    @Test
    fun `name getter setter`() {
        val newBean = ClientPropertiesBean()
        assertEquals("DEFAULT_NAME", newBean.name)
        newBean.name = "TEST_NAME_ONE"
        assertEquals("TEST_NAME_ONE", newBean.name)
    }

    @Test
    fun `business getter setter`() {
        val newBean = ClientPropertiesBean()
        assertTrue(newBean.businessInterest)
        newBean.businessInterest = false
        @Suppress("KotlinConstantConditions")
        assertFalse(newBean.businessInterest)
    }

    @Test
    fun `science getter setter`() {
        val newBean = ClientPropertiesBean()
        assertTrue(newBean.scienceNewsInterest)
        newBean.scienceNewsInterest = false
        @Suppress("KotlinConstantConditions")
        assertFalse(newBean.scienceNewsInterest)
    }

    @Test
    fun `sports getter setter`() {
        val newBean = ClientPropertiesBean()
        assertTrue(newBean.sportsInterest)
        newBean.sportsInterest = false
        @Suppress("KotlinConstantConditions")
        assertFalse(newBean.sportsInterest)
    }

    @Test
    fun `world getter setter`() {
        val newBean = ClientPropertiesBean()
        assertTrue(newBean.worldNewsInterest)
        newBean.worldNewsInterest = false
        @Suppress("KotlinConstantConditions")
        assertFalse(newBean.worldNewsInterest)
    }

    @Test
    fun `request constructor`() {
        val newBean = ClientPropertiesBean(mock())
        assertEquals("DEFAULT_NAME", newBean.name)
        assertFalse(newBean.businessInterest)
        assertFalse(newBean.scienceNewsInterest)
        assertFalse(newBean.sportsInterest)
        assertFalse(newBean.worldNewsInterest)
    }
}