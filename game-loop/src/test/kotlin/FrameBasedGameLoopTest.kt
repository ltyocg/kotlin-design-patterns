import kotlin.test.Test
import kotlin.test.assertEquals

class FrameBasedGameLoopTest {
    private val gameLoop = FrameBasedGameLoop()

    @Test
    fun `test update`() {
        gameLoop.update()
        assertEquals(0.5f, gameLoop.controller.bulletPosition, 0f)
    }
}