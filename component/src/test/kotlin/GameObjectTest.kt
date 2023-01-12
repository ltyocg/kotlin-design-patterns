import org.slf4j.LoggerFactory
import java.awt.event.KeyEvent
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GameObjectTest {
    private lateinit var playerTest: GameObject
    private lateinit var npcTest: GameObject

    private companion object {
        private val log = LoggerFactory.getLogger(GameObjectTest::class.java)
    }

    @BeforeTest
    fun initEach() {
        playerTest = GameObject.createPlayer()
        npcTest = GameObject.createNpc()
    }

    @Test
    fun `object`() {
        log.info("objectTest:")
        assertEquals("player", playerTest.name)
        assertEquals("npc", npcTest.name)
    }

    @Test
    fun `event input`() {
        log.info("eventInputTest:")
        playerTest.update(KeyEvent.KEY_LOCATION_LEFT)
        assertEquals(-1, playerTest.velocity)
        assertEquals(-1, playerTest.coordinate)
        playerTest.update(KeyEvent.KEY_LOCATION_RIGHT)
        playerTest.update(KeyEvent.KEY_LOCATION_RIGHT)
        assertEquals(1, playerTest.velocity)
        assertEquals(0, playerTest.coordinate)
        log.info(playerTest.coordinate.toString())
        log.info(playerTest.velocity.toString())
        val p2 = GameObject.createPlayer()
        p2.update(KeyEvent.KEY_LOCATION_LEFT)
        p2.update(KeyEvent.KEY_LOCATION_UNKNOWN)
        assertEquals(-1, p2.velocity)
    }

    @Test
    fun `npc demo`() {
        log.info("npcDemoTest:")
        npcTest.demoUpdate()
        assertEquals(2, npcTest.velocity)
        assertEquals(2, npcTest.coordinate)
    }
}