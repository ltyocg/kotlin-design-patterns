import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory
import java.util.concurrent.LinkedBlockingQueue

private val log = LoggerFactory.getLogger("main")

fun main() {
    with(AsynchronousService(LinkedBlockingQueue())) {
        listOf<Long>(1000, 500, 2000, 1).forEach { execute(ArithmeticSumTask(it)) }
        close()
    }
}

class ArithmeticSumTask(private val numberOfElements: Long) : AsyncTask<Long> {
    override fun call(): Long = runBlocking {
        delay(numberOfElements)
        numberOfElements * (numberOfElements + 1) / 2
    }

    override fun onPreCall() {
        require(numberOfElements >= 0) { "n is less than 0" }
    }

    override fun onPostCall(result: Long) {
        log.info(result.toString())
    }

    override fun onError(throwable: Throwable) {
        error("Should not occur")
    }
}