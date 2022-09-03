import org.slf4j.LoggerFactory

class BallItem : GameItem() {
    private val log = LoggerFactory.getLogger(javaClass)
    private var isSuspended = false
    lateinit var twin: BallThread
    override fun doDraw() = log.info("doDraw")
    fun move() = log.info("move")
    override fun click() {
        isSuspended = !isSuspended
        if (isSuspended) twin.suspendMe()
        else twin.resumeMe()
    }
}