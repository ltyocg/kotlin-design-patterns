package com.ltyocg.gameloop

class VariableStepGameLoop : GameLoop() {
    override suspend fun processGameLoop() {
        var lastFrameTime = System.currentTimeMillis()
        while (isGameRunning) {
            processInput()
            val currentFrameTime = System.currentTimeMillis()
            update(currentFrameTime - lastFrameTime)
            lastFrameTime = currentFrameTime
            render()
        }
    }

    fun update(elapsedTime: Long) {
        controller.moveBullet(0.5f * elapsedTime / 1000)
    }
}