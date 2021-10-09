package com.ltyocg.gameloop

class FrameBasedGameLoop : GameLoop() {
    override suspend fun processGameLoop() {
        while (isGameRunning) {
            processInput()
            update()
            render()
        }
    }

    internal fun update() {
        controller.moveBullet(0.5f)
    }
}