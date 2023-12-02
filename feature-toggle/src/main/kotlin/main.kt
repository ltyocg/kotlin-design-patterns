import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}

fun main() {
    logger.info {
        PropertiesFeatureToggleVersion(Properties().apply {
            put("enhancedWelcome", true)
        }).getWelcomeMessage(User("Jamie No Code"))
    }
    logger.info {
        PropertiesFeatureToggleVersion(Properties().apply {
            put("enhancedWelcome", false)
        }).getWelcomeMessage(User("Jamie No Code"))
    }
    val service = TieredFeatureToggleVersion()
    val paidUser = User("Jamie Coder")
    val freeUser = User("Alan Defect")
    UserGroup.addUserToPaidGroup(paidUser)
    UserGroup.addUserToFreeGroup(freeUser)
    logger.info { service.getWelcomeMessage(paidUser) }
    logger.info { service.getWelcomeMessage(freeUser) }
}
