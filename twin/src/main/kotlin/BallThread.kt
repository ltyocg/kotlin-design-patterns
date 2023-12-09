import io.github.oshai.kotlinlogging.KotlinLogging

class BallThread : Thread() {
    private val logger = KotlinLogging.logger {}
    lateinit var twin: BallItem

    @Volatile
    private var isSuspended = false

    @Volatile
    private var isRunning = true
    override fun run() {
        while (isRunning) {
            if (!isSuspended) {
                twin.draw()
                twin.move()
            }
            try {
                sleep(250)
            } catch (e: InterruptedException) {
                throw RuntimeException(e)
            }
        }
    }

    fun suspendMe() {
        isSuspended = true
        logger.info { "Begin to suspend BallThread" }
    }

    fun resumeMe() {
        isSuspended = false
        logger.info { "Begin to resume BallThread" }
    }

    fun stopMe() {
        isRunning = false
        isSuspended = true
    }
}
