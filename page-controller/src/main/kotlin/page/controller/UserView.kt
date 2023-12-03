package page.controller

import io.github.oshai.kotlinlogging.KotlinLogging

class UserView {
    private val logger = KotlinLogging.logger {}
    fun display(user: UserModel): String {
        logger.info { "display user html name ${user.name} email ${user.email}" }
        return "/user"
    }
}
