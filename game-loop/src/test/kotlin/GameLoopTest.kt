import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class GameLoopTest {
    private val gameLoop = object : GameLoop() {
        override suspend fun processGameLoop() {}
    }

    @Test
    fun `test run`() {
        gameLoop.run()
        assertEquals(GameStatus.RUNNING, gameLoop.status)
    }

    @Test
    fun `test stop`() {
        gameLoop.stop()
        assertEquals(GameStatus.STOPPED, gameLoop.status)
    }

    @Test
    fun `test isGameRunning`() {
        assertFalse(gameLoop.isGameRunning)
    }
}