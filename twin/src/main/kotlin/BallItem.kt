import io.github.oshai.kotlinlogging.KotlinLogging

class BallItem : GameItem() {
    private val logger = KotlinLogging.logger {}
    private var isSuspended = false
    lateinit var twin: BallThread
    override fun doDraw() = logger.info { "doDraw" }
    fun move() = logger.info { "move" }
    override fun click() {
        isSuspended = !isSuspended
        if (isSuspended) twin.suspendMe()
        else twin.resumeMe()
    }
}
