import org.slf4j.LoggerFactory

class Consumer(private val name: String, private val queue: ItemQueue) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun consume() {
        val (producer, id) = queue.take()
        log.info("Consumer [{}] consume item [{}] produced by [{}]", name, id, producer)
    }
}