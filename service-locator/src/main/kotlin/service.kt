import org.slf4j.LoggerFactory
import kotlin.math.floor

interface Service {
    val name: String
    val id: Int
    fun execute()
}

class ServiceImpl(override val name: String) : Service {
    private val log = LoggerFactory.getLogger(javaClass)
    override val id = floor(Math.random() * 1000).toInt() + 1
    override fun execute() = log.info("Service {} is now executing with id {}", name, id)
}