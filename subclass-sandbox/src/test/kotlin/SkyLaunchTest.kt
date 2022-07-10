import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class SkyLaunchTest {
    @Test
    fun move() {
        val assertListAppender = assertListAppender(SkyLaunch::class)
        SkyLaunch().move(1.0, 1.0, 1.0)
        assertEquals("Move to ( 1.0, 1.0, 1.0 )", assertListAppender.lastMessage())
    }

    @Test
    fun playSound() {
        val assertListAppender = assertListAppender(SkyLaunch::class)
        SkyLaunch().playSound("SOUND_NAME", 1)
        assertEquals("Play SOUND_NAME with volumn 1", assertListAppender.lastMessage())
    }

    @Test
    fun spawnParticles() {
        val assertListAppender = assertListAppender(SkyLaunch::class)
        SkyLaunch().spawnParticles("PARTICLE_TYPE", 100)
        assertEquals("Spawn 100 particle with type PARTICLE_TYPE", assertListAppender.lastMessage())
    }

    @Test
    fun activate() {
        val assertListAppender = assertListAppender(SkyLaunch::class)
        SkyLaunch().activate()
        assertEquals(3, assertListAppender.list.size)
        assertContentEquals(
            listOf(
                "Move to ( 0.0, 0.0, 20.0 )",
                "Play SKYLAUNCH_SOUND with volumn 1",
                "Spawn 100 particle with type SKYLAUNCH_PARTICLE"
            ),
            assertListAppender.formattedList()
        )
    }
}