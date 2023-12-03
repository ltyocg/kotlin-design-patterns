import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.reflect.full.findAnnotation

private val logger = KotlinLogging.logger {}
fun main() {
    val guard = Guard()
    val thief = Thief()
    if (guard::class.findAnnotation<Permission>() != null) guard.enter()
    else logger.info { "You have no permission to enter, please leave this area" }
    if (thief::class.findAnnotation<Permission>() != null) thief.steal()
    else thief.doNothing()
}
