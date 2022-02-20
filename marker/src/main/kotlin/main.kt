import org.slf4j.LoggerFactory
import kotlin.reflect.full.findAnnotation

private val log = LoggerFactory.getLogger("main")
fun main() {
    val guard = Guard()
    val thief = Thief()
    if (guard::class.findAnnotation<Permission>() != null) guard.enter()
    else log.info("You have no permission to enter, please leave this area")
    if (thief::class.findAnnotation<Permission>() != null) thief.steal()
    else thief.doNothing()
}