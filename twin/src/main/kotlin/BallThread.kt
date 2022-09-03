import org.slf4j.LoggerFactory

class BallThread : Thread() {
    private val log = LoggerFactory.getLogger(javaClass)
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
        log.info("Begin to suspend BallThread")
    }

    fun resumeMe() {
        isSuspended = false
        log.info("Begin to resume BallThread")
    }

    fun stopMe() {
        isRunning = false
        isSuspended = true
    }
}