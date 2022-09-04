import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WorldTest {
    companion object {
        private lateinit var world: World

        @JvmStatic
        @BeforeAll
        fun setup() {
            world = World()
        }
    }

    @Test
    fun run() {
        world.run()
        assertTrue(world.isRunning)
    }

    @Test
    fun stop() {
        world.stop()
        assertFalse(world.isRunning)
    }

    @Test
    fun addEntity() {
        val entity = Skeleton(1)
        world.addEntity(entity)
        assertEquals(entity, world.entities[0])
    }
}