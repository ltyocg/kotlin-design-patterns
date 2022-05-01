import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit

private val log = LoggerFactory.getLogger("main")
suspend fun main() = withContext<Unit>(Dispatchers.Default) {
    val world = World()
    launch {
        while (true) {
            log.info("Our world currently has the following countries:-")
            world.fetch().asSequence().map { "\t$it" }.forEach(log::info)
            delay(TimeUnit.SECONDS.toMillis(15))
        }
    }
}