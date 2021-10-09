package com.ltyocg.gameloop

import kotlin.test.Test
import kotlin.test.assertEquals

class GameControllerTest {
    private val controller = GameController()

    @Test
    fun `test moveBullet`() {
        controller.moveBullet(1.5f)
        assertEquals(1.5f, controller.bullet.position, 0f)
    }

    @Test
    fun `test bulletPosition`() {
        assertEquals(controller.bullet.position, controller.bulletPosition, 0f)
    }
}