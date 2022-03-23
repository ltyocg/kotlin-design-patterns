import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.IOException

private val log = LoggerFactory.getLogger("main")
fun main() {
    useOfLoggedMute()
    useOfMute()
}

private fun useOfLoggedMute() {
    val resource = acquireResource()
    try {
        utilizeResource(resource)
    } finally {
        closeResource(resource)
    }
}

private fun useOfMute() {
    Mute.mute { ByteArrayOutputStream().write("Hello".toByteArray()) }
}

private fun acquireResource(): Resource = object : Resource {
    override fun close() = throw IOException("Error in closing resource: $this")
}

private fun utilizeResource(resource: Resource) = log.info("Utilizing acquired resource: {}", resource)
private fun closeResource(resource: Resource) = Mute.loggedMute(resource::close)