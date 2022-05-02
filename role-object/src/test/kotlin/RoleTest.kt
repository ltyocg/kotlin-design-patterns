import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class RoleTest {
    @Test
    fun instanceTest() {
        val instance = Role.Borrower.instance()
        assertNotNull(instance)
        assertEquals(instance::class, BorrowerRole::class)
    }
}