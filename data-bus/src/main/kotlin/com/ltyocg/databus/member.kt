package com.ltyocg.databus

import org.slf4j.LoggerFactory
import java.time.LocalDateTime

fun interface Member : (DataType) -> Unit {
    override fun invoke(data: DataType)
}

class MessageCollectorMember(private val name: String) : Member {
    private val log = LoggerFactory.getLogger(javaClass)
    private val _messages = mutableListOf<String>()
    val messages: List<String>
        get() = _messages.toList()

    override fun invoke(data: DataType) {
        if (data is MessageData) {
            log.info("{} sees message {}", name, data.message)
            _messages.add(data.message)
        }
    }
}

class StatusMember(private val id: Int) : Member {
    private val log = LoggerFactory.getLogger(javaClass)
    var started: LocalDateTime? = null
        private set
    var stopped: LocalDateTime? = null
        private set

    override fun invoke(data: DataType) {
        when (data) {
            is StartingData -> {
                started = data.`when`
                log.info("Receiver {} sees application started at {}", id, started)
            }
            is StoppingData -> {
                stopped = data.`when`
                log.info("Receiver {} sees application stopping at {}", id, stopped)
                log.info("Receiver {} sending goodbye message", id)
                data.dataBus.publish(MessageData("Goodbye cruel world from #%d!".format(id)))
            }
        }
    }
}