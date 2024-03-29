import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.LinkedBlockingQueue

private val logger = KotlinLogging.logger {}
fun main() {
    with(AsynchronousService(LinkedBlockingQueue())) {
        listOf<Long>(1000, 500, 2000, 1).forEach { execute(ArithmeticSumTask(it)) }
        close()
    }
}

class ArithmeticSumTask(private val numberOfElements: Long) : AsyncTask<Long> {
    override fun invoke(): Long = runBlocking {
        delay(numberOfElements)
        numberOfElements * (numberOfElements + 1) / 2
    }

    override fun onPreCall() = require(numberOfElements >= 0) { "n is less than 0" }
    override fun onPostCall(result: Long) = logger.info { result }
    override fun onError(throwable: Throwable) = error("Should not occur")
}
