import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.ByteArrayOutputStream
import java.io.IOException

private val logger = KotlinLogging.logger {}
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

private fun utilizeResource(resource: Resource) = logger.info { "Utilizing acquired resource: $resource" }
private fun closeResource(resource: Resource) = Mute.loggedMute(resource::close)
