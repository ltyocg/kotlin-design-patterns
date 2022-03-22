import org.slf4j.LoggerFactory
import utils.DatabaseUtil

private val log = LoggerFactory.getLogger("main")
fun main() {
    DatabaseUtil.init()
    val userService = UserService()
    generateSampleUsers().forEach {
        log.info("Add user{}at{}.", it, userService.createUser(it))
    }
    log.info(userService.listUser().toString())
    val user = userService.getUser(1)
    log.info(user.toString())
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