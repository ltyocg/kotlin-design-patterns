import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}
suspend fun main() = runBlocking<Unit> {
    val world = World()
    launch {
        while (true) {
            logger.info { "Our world currently has the following countries:-" }
            world.fetch().forEach { logger.info { "\t$it" } }
            delay(TimeUnit.SECONDS.toMillis(15))
        }
    }
}
