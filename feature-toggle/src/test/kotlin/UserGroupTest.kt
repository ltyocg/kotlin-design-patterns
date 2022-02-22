import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserGroupTest {
    @Test
    fun `test addUserToFreeGroup`() {
        val user = User("Free User")
        UserGroup.addUserToFreeGroup(user)
        assertFalse(UserGroup.isPaid(user))
    }

    @Test
    fun `test addUserToPaidGroup`() {
        val user = User("Paid User")
        UserGroup.addUserToPaidGroup(user)
        assertTrue(UserGroup.isPaid(user))
    }

    @Test
    fun `test add user to paid when on free`() {
        val user = User("Paid User")
        UserGroup.addUserToFreeGroup(user)
        assertFailsWith<IllegalArgumentException> { UserGroup.addUserToPaidGroup(user) }
    }

    @Test
    fun `test add user to free when on paid`() {
        val user = User("Free User")
        UserGroup.addUserToPaidGroup(user)
        assertFailsWith<IllegalArgumentException> { UserGroup.addUserToFreeGroup(user) }
    }
}