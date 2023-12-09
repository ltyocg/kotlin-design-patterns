import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.floor

interface Service {
    val name: String
    val id: Int
    fun execute()
}

class ServiceImpl(override val name: String) : Service {
    private val logger = KotlinLogging.logger {}
    override val id = floor(Math.random() * 1000).toInt() + 1
    override fun execute() = logger.info { "Service $name is now executing with id $id" }
}
