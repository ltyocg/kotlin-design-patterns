import io.github.oshai.kotlinlogging.KotlinLogging
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

abstract class EventJournal(internal val file: File) {
    private val logger = KotlinLogging.logger {}
    abstract fun write(domainEvent: DomainEvent)
    fun reset() {
        if (file.delete()) logger.info { "File cleared successfully............" }
    }

    abstract fun readNext(): DomainEvent
}

class JsonFileJournal : EventJournal(File("Journal.jsonl")) {
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
    private val events =
        if (file.exists()) file.readLines()
        else {
            reset()
            emptyList()
        }
    private var index = 0
    override fun write(domainEvent: DomainEvent) = file.appendText(json.encodeToString(domainEvent) + System.lineSeparator())
    override fun readNext(): DomainEvent =
        if (index < events.size) json.decodeFromString<DomainEvent>(events[index++])
        else throw RuntimeException("Failed to convert JSON")
}
