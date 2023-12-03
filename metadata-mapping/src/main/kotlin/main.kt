import io.github.oshai.kotlinlogging.KotlinLogging
import utils.DatabaseUtil

private val logger = KotlinLogging.logger {}
fun main() {
    DatabaseUtil.init()
    val userService = UserService()
    generateSampleUsers().forEach {
        logger.info { "Add user${it}at${userService.createUser(it)}." }
    }
    logger.info { userService.listUser() }
    val user = userService.getUser(1)
    logger.info { user }
    user!!.password = "new123"
    userService.updateUser(1, user)
    userService.deleteUser(2)
    userService.close()
}

private fun generateSampleUsers(): List<User> = listOf(
    User(username = "ZhangSan", password = "zhs123"),
    User(username = "LiSi", password = "ls123"),
    User(username = "WangWu", password = "ww123")
)
