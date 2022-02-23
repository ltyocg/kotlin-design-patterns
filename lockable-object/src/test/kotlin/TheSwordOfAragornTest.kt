import domain.Human
import kotlin.test.*

class TheSwordOfAragornTest {
    @Test
    fun `basic sword`() {
        val sword = SwordOfAragorn()
        assertNotNull(sword.name)
        assertNull(sword.locker)
        assertFalse(sword.isLocked)
        val human = Human("Tupac")
        assertTrue(human.acquire(sword))
        assertEquals(human, sword.locker)
        assertTrue(sword.isLocked)
    }
}