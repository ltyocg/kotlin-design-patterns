package page.controller

import io.github.oshai.kotlinlogging.KotlinLogging

class SignupView {
    private val logger = KotlinLogging.logger {}
    fun display(): String {
        logger.info { "display signup front page" }
        return "/signup"
    }

    fun redirect(form: SignupModel): String {
        logger.info { "Redirect to user page with name ${form.name} email ${form.email}" }
        return "redirect:/user"
    }
}
