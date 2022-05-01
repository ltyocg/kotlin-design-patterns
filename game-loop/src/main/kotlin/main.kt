import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
suspend fun main() {
    FrameBasedGameLoop().runAround("frame-based")
    VariableStepGameLoop().runAround("variable-step")
    FixedStepGameLoop().runAround("fixed-step")
}

private suspend fun GameLoop.runAround(name: String) {
    log.info("Start $name game loop:")
    run()
    delay(2000L)
    stop()
    log.info("Stop $name game loop.")
}