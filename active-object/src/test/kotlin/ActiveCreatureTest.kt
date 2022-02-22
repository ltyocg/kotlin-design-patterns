import kotlin.test.Test
import kotlin.test.assertEquals

class ActiveCreatureTest {
    @Test
    fun `execution test`() {
        val orc = Orc("orc1")
        assertEquals("orc1", orc.name)
        assertEquals(0, orc.status)
        with(orc) {
            eat()
            roam()
            kill()
        }
    }
}