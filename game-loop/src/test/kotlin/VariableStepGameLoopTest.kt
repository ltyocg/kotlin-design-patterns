import kotlin.test.Test
import kotlin.test.assertEquals

class VariableStepGameLoopTest {
    private val gameLoop = VariableStepGameLoop()

    @Test
    fun `test update`() {
        gameLoop.update(20)
        assertEquals(0.01f, gameLoop.controller.bulletPosition, 0f)
    }
}