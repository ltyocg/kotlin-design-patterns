import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay

private val logger = KotlinLogging.logger {}
suspend fun main() {
    FrameBasedGameLoop().runAround("frame-based")
    VariableStepGameLoop().runAround("variable-step")
    FixedStepGameLoop().runAround("fixed-step")
}

private suspend fun GameLoop.runAround(name: String) {
    logger.info { "Start $name game loop:" }
    run()
    delay(2000L)
    stop()
    logger.info { "Stop $name game loop." }
}
