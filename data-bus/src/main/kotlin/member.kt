import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.LocalDateTime

fun interface Member : (DataType) -> Unit {
    override fun invoke(data: DataType)
}

class MessageCollectorMember(private val name: String) : Member {
    private val logger = KotlinLogging.logger {}
    private val _messages = mutableListOf<String>()
    val messages: List<String>
        get() = _messages.toList()

    override fun invoke(data: DataType) {
        if (data is MessageData) {
            logger.info { "$name sees message ${data.message}" }
            _messages.add(data.message)
        }
    }
}

class StatusMember(private val id: Int) : Member {
    private val logger = KotlinLogging.logger {}
    var started: LocalDateTime? = null
        private set
    var stopped: LocalDateTime? = null
        private set

    override fun invoke(data: DataType) {
        when (data) {
            is StartingData -> {
                started = data.`when`
                logger.info { "Receiver $id sees application started at $started" }
            }

            is StoppingData -> {
                stopped = data.`when`
                logger.info { "Receiver $id sees application stopping at $stopped" }
                logger.info { "Receiver $id sending goodbye message" }
                DataBus.publish(MessageData("Goodbye cruel world from #$id!"))
            }
        }
    }
}
