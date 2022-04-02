import kotlin.concurrent.thread

fun main() {
    val queue = SimpleMessageQueue(10000)
    val producer = Producer("PRODUCER_1", queue)
    val consumer = Consumer("CONSUMER_1", queue)
    thread { consumer.consume() }
    thread {
        producer.send("hand shake")
        producer.send("some very important information")
        producer.send("bye!")
        producer.stop()
    }
}