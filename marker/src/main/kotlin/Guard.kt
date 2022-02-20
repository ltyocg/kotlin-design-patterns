import org.slf4j.LoggerFactory

@Permission
class Guard {
    private val log = LoggerFactory.getLogger(javaClass)
    fun enter() = log.info("You can enter")
}