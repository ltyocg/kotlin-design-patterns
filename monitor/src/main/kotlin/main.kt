import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.logging.Logger

fun main() {
    val bank = Bank(4, 1000, Logger.getLogger("monitor"))
    runBlocking {
        repeat(5) {
            launch {
                delay((Math.random() * 1000).toLong())
                val random = Random()
                repeat(1000000) {
                    bank.transfer(random.nextInt(4), random.nextInt(4), (Math.random() * 1000).toInt())
                }
            }
        }
    }
}