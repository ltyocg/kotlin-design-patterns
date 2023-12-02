import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.io.File
import java.math.BigDecimal

class DomainEventProcessor {
    private val processorJournal = JsonFileJournal()
    fun process(domainEvent: DomainEvent) {
        domainEvent.process()
        processorJournal.write(domainEvent)
    }

    fun reset() = processorJournal.reset()
    fun recover() = generateSequence { processorJournal.readNext() }.forEach { it.process() }
    private class JsonFileJournal {
        private val json = Json {
            classDiscriminator = "eventClassName"
            serializersModule = SerializersModule {
                polymorphic(DomainEvent::class) {
                    subclass(AccountCreateEvent::class)
                    subclass(MoneyDepositEvent::class)
                    subclass(MoneyTransferEvent::class)
                }
                contextual(BigDecimal::class, object : KSerializer<BigDecimal> {
                    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(this::class.qualifiedName ?: "(anonymous class)", PrimitiveKind.STRING)
                    override fun deserialize(decoder: Decoder): BigDecimal = decoder.decodeString().toBigDecimal()
                    override fun serialize(encoder: Encoder, value: BigDecimal) {
                        encoder.encodeString(value.toPlainString())
                    }
                })
            }
        }
        private val file = File("Journal.jsonl")
        private val events =
            if (file.exists()) file.readLines()
            else {
                reset()
                emptyList()
            }
        private var index = 0

        fun write(domainEvent: DomainEvent) = file.appendText(json.encodeToString(domainEvent) + System.getProperty("line.separator"))
        fun reset() {
            file.delete()
        }

        fun readNext(): DomainEvent? =
            if (index < events.size) json.decodeFromString<DomainEvent>(events[index++])
            else null
    }
}
