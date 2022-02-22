import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.convertValue
import java.io.File

class DomainEventProcessor {
    private val processorJournal = JsonFileJournal()
    fun process(domainEvent: DomainEvent) {
        domainEvent.process()
        processorJournal.write(domainEvent)
    }

    fun reset() {
        processorJournal.reset()
    }

    fun recover() {
        generateSequence { processorJournal.readNext() }.forEach { it.process() }
    }

    private class JsonFileJournal {
        private val file = File("Journal.json")
        private val events = mutableListOf<String>()
        private var index = 0

        init {
            if (file.exists()) events.addAll(file.readLines())
            else reset()
        }

        fun write(domainEvent: DomainEvent) {
            file.appendText(ObjectMapper().writeValueAsString(domainEvent) + System.getProperty("line.separator"))
        }

        fun reset() {
            file.delete()
        }

        fun readNext(): DomainEvent? {
            if (index >= events.size) return null
            val objectMapper = ObjectMapper()
            val objectNode = objectMapper.readTree(events[index++]) as ObjectNode
            return when (objectNode["eventClassName"].asText()) {
                AccountCreateEvent::class.simpleName -> objectMapper.convertValue<AccountCreateEvent>(objectNode)
                MoneyDepositEvent::class.simpleName -> objectMapper.convertValue<MoneyDepositEvent>(objectNode)
                MoneyTransferEvent::class.simpleName -> objectMapper.convertValue<MoneyTransferEvent>(objectNode)
                else -> throw RuntimeException("Journal Event not recegnized")
            }.also { it.realTime = false }
        }
    }
}