import org.slf4j.LoggerFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

private const val DEFAULT_URL = "https://raw.githubusercontent.com/iluwatar/java-design-patterns/master/promise/README.md"
private val log = LoggerFactory.getLogger("main")
private val executor = Executors.newFixedThreadPool(2)
private val stopLatch = CountDownLatch(2)
fun main() {
    try {
        calculateLineCount()
        calculateLowestFrequencyChar()
    } finally {
        stopLatch.await()
        executor.shutdownNow()
    }
}

private fun calculateLineCount() {
    download(DEFAULT_URL)
        .thenApply { Utility.countLines(it!!) }
        .thenAccept {
            log.info("Line count is: {}", it)
            taskCompleted()
        }
}

private fun calculateLowestFrequencyChar() {
    download(DEFAULT_URL)
        .thenApply { Utility.characterFrequency(it!!) }
        .thenApply { Utility.lowestFrequencyChar(it!!) }
        .thenAccept {
            log.info("Char with lowest frequency is: {}", it)
            taskCompleted()
        }
}

private fun download(urlString: String): Promise<String> = Promise<String>()
    .fulfillInAsync(executor) { Utility.downloadFile(urlString) }
    .onError {
        it?.printStackTrace()
        taskCompleted()
    }

private fun taskCompleted() = stopLatch.countDown()