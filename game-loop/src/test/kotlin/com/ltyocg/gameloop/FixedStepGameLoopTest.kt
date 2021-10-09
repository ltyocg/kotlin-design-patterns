package com.ltyocg.gameloop

import kotlin.test.Test
import kotlin.test.assertEquals

class FixedStepGameLoopTest {
    private val gameLoop = FixedStepGameLoop()

    @Test
    fun `test update`() {
        gameLoop.update()
        assertEquals(0.01f, gameLoop.controller.bulletPosition, 0f)
    }
}