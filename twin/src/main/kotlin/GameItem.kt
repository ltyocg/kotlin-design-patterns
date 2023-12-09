import io.github.oshai.kotlinlogging.KotlinLogging

abstract class GameItem {
    private val logger = KotlinLogging.logger {}
    fun draw() {
        logger.info { "draw" }
        doDraw()
    }

    abstract fun doDraw()
    abstract fun click()
}
