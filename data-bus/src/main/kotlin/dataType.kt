import java.time.LocalDateTime

interface DataType {
    var dataBus: DataBus
}

abstract class AbstractDataType : DataType {
    override lateinit var dataBus: DataBus
}

class MessageData(val message: String) : AbstractDataType()
class StartingData(val `when`: LocalDateTime) : AbstractDataType()
class StoppingData(val `when`: LocalDateTime) : AbstractDataType()