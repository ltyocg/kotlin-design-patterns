import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class GroundDiveTest {
    @Test
    fun move() {
        val assertListAppender = assertListAppender(GroundDive::class)
        GroundDive().move(1.0, 1.0, 1.0)
        assertEquals("Move to ( 1.0, 1.0, 1.0 )", assertListAppender.lastMessage())
    }

    @Test
    fun playSound() {
        val assertListAppender = assertListAppender(GroundDive::class)
        GroundDive().playSound("SOUND_NAME", 1)
        assertEquals("Play SOUND_NAME with volumn 1", assertListAppender.lastMessage())
    }

    @Test
    fun spawnParticles() {
        val assertListAppender = assertListAppender(GroundDive::class)
        GroundDive().spawnParticles("PARTICLE_TYPE", 100)
        assertEquals("Spawn 100 particle with type PARTICLE_TYPE", assertListAppender.lastMessage())
    }

    @Test
    fun activate() {
        val assertListAppender = assertListAppender(GroundDive::class)
        GroundDive().activate()
        assertEquals(3, assertListAppender.list.size)
        assertContentEquals(
            listOf(
                "Move to ( 0.0, 0.0, -20.0 )",
                "Play GROUNDDIVE_SOUND with volumn 5",
                "Spawn 20 particle with type GROUNDDIVE_PARTICLE"
            ),
            assertListAppender.formattedList()
        )
    }
}