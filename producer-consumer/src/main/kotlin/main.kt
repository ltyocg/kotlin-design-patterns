import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private val log = LoggerFactory.getLogger("main")
fun main() {
    val queue = ItemQueue()
    val executorService = Executors.newFixedThreadPool(5)
    repeat(2) {
        val producer = Producer("Producer_$it", queue)
        executorService.submit {
            while (true) producer.produce()
        }
    }
    repeat(2) {
        val consumer = Consumer("Consumer_$it", queue)
        executorService.submit {
            while (true) consumer.consume()
        }
    }
    executorService.shutdown()
    try {
        executorService.awaitTermination(10, TimeUnit.SECONDS)
        executorService.shutdownNow()
    } catch (e: InterruptedException) {
        log.error("Error waiting for ExecutorService shutdown")
    }
}