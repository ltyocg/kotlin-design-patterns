import kotlin.test.Test
import kotlin.test.assertEquals

class BorrowerRoleTest {
    @Test
    fun borrow() =
        assertEquals("Borrower test wants to get some money.", BorrowerRole().apply { name = "test" }.borrow())
}