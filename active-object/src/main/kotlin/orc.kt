import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

abstract class ActiveCreature
internal constructor(val name: String) {
    private val logger = KotlinLogging.logger {}
    private val coroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val coroutineScope = CoroutineScope(coroutineDispatcher)
    var status = 0
        private set

    fun eat() = invocation {
        logger.info { "$name is eating!" }
        logger.info { "$name has finished eating!" }
    }

    fun roam() = invocation {
        logger.info { "$name has started to roam in the wastelands." }
    }

    fun kill(status: Int = 0) {
        this.status = status
        coroutineScope.cancel()
        coroutineDispatcher.close()
    }

    private fun invocation(block: () -> Unit) {
        coroutineScope.launch { block() }.invokeOnCompletion {
            if (it != null && status != 0) logger.error { "Job was interrupted. --> ${it.localizedMessage}" }
        }
    }
}

class Orc(name: String) : ActiveCreature(name)
