class FixedStepGameLoop : GameLoop() {
    private companion object {
        private const val MS_PER_FRAME = 20
    }

    override suspend fun processGameLoop() {
        var previousTime = System.currentTimeMillis()
        var lag = 0L
        while (isGameRunning) {
            val currentTime = System.currentTimeMillis()
            lag += currentTime - previousTime
            previousTime = currentTime
            processInput()
            while (lag >= MS_PER_FRAME) {
                update()
                lag -= MS_PER_FRAME
            }
            render()
        }
    }

    internal fun update() = controller.moveBullet(0.5f * MS_PER_FRAME / 1000)
}