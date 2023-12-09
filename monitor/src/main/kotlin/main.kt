import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

private val logger = KotlinLogging.logger {}
suspend fun main() {
    val bank = Bank(4, 1000)
    withContext(Dispatchers.Default) {
        repeat(5) {
            launch { runner(bank) }
        }
    }
}

suspend fun runner(bank: Bank) {
    delay((Math.random() * 1000).toLong())
    logger.info { "Start transferring..." }
    val random = Random()
    repeat(1000000) {
        bank.transfer(random.nextInt(4), random.nextInt(4), (Math.random() * 1000).toInt())
    }
    logger.info { "Finished transferring." }
}
