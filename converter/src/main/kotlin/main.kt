import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val userConverter = UserConverter()
    log.info("Entity converted from DTO: {}", userConverter.convertFromDto(UserDto("John", "Doe", true, "whatever[at]wherever.com")))
    val users = listOf(
        User("Camile", "Tough", false, "124sad"),
        User("Marti", "Luther", true, "42309fd"),
        User("Kate", "Smith", true, "if0243")
    )
    log.info("Domain entities:")
    users.map(User::toString).forEach(log::info)
    log.info("DTO entities converted from domain:")
    userConverter.createFromEntities(users).map(UserDto::toString).forEach(log::info)
}