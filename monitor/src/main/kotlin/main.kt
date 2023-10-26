import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.util.*

private val log = LoggerFactory.getLogger("main")
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
    log.info("Start transferring...")
    val random = Random()
    repeat(1000000) {
        bank.transfer(random.nextInt(4), random.nextInt(4), (Math.random() * 1000).toInt())
    }
    log.info("Finished transferring.")
}