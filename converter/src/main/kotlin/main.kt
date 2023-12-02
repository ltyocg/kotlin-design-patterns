import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Entity converted from DTO: ${UserConverter.convertFromDto(UserDto("John", "Doe", true, "whatever[at]wherever.com"))}" }
    val users = listOf(
        User("Camile", "Tough", false, "124sad"),
        User("Marti", "Luther", true, "42309fd"),
        User("Kate", "Smith", true, "if0243")
    )
    logger.info { "Domain entities:" }
    users.forEach { logger.info { it } }
    logger.info { "DTO entities converted from domain:" }
    UserConverter.createFromEntities(users).forEach { logger.info { it } }
}
