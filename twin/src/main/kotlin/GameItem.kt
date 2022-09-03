import org.slf4j.LoggerFactory

abstract class GameItem {
    private val log = LoggerFactory.getLogger(javaClass)
    fun draw() {
        log.info("draw")
        doDraw()
    }

    abstract fun doDraw()
    abstract fun click()
}