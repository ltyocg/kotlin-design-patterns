import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.*
import java.security.SecureRandom

abstract class GameLoop {
    private val logger = KotlinLogging.logger {}

    @Volatile
    internal var status = GameStatus.STOPPED
    internal val controller = GameController()
    private var gameJob: Job? = null
    fun run() {
        status = GameStatus.RUNNING
        gameJob = CoroutineScope(Dispatchers.Default).launch { processGameLoop() }
    }

    fun stop() {
        status = GameStatus.STOPPED
    }

    val isGameRunning: Boolean
        get() = status == GameStatus.RUNNING

    protected suspend fun processInput() = delay(SecureRandom().nextInt(200) + 50L)
    protected fun render() = logger.info { "Current bullet position: ${controller.bulletPosition}" }
    protected abstract suspend fun processGameLoop()
}
