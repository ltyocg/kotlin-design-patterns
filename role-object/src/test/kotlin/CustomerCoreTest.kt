import kotlin.test.*

internal class CustomerCoreTest {
    @Test
    fun addRole() = assertTrue(CustomerCore().addRole(Role.Borrower))

    @Test
    fun hasRole() {
        val core = CustomerCore()
        core.addRole(Role.Borrower)
        assertTrue(core.hasRole(Role.Borrower))
        assertFalse(core.hasRole(Role.Investor))
    }

    @Test
    fun remRole() {
        val core = CustomerCore()
        core.addRole(Role.Borrower)
        assertNotNull(core.getRole(Role.Borrower, BorrowerRole::class))
        assertTrue(core.remRole(Role.Borrower))
        assertNull(core.getRole(Role.Borrower, BorrowerRole::class))
    }

    @Test
    fun getRole() {
        val core = CustomerCore()
        core.addRole(Role.Borrower)
        assertNotNull(core.getRole(Role.Borrower, BorrowerRole::class))
        assertNull(core.getRole(Role.Borrower, InvestorRole::class))
        assertNull(core.getRole(Role.Investor, InvestorRole::class))
    }

    @Test
    fun `toString test`() {
        var core = CustomerCore()
        core.addRole(Role.Borrower)
        assertEquals("Customer{roles=[Borrower]}", core.toString())
        core = CustomerCore()
        core.addRole(Role.Investor)
        assertEquals("Customer{roles=[Investor]}", core.toString())
        assertEquals("Customer{roles=[]}", CustomerCore().toString())
    }
}