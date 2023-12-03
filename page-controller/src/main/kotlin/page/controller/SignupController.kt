package page.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class SignupController {
    private val logger = KotlinLogging.logger {}
    private val view = SignupView()

    @GetMapping("signup")
    fun getSignup(): String = view.display()

    @PostMapping("signup")
    fun create(form: SignupModel, redirectAttributes: RedirectAttributes): String {
        logger.info { form.name }
        logger.info { form.email }
        redirectAttributes.addAttribute("name", form.name)
        redirectAttributes.addAttribute("email", form.email)
        redirectAttributes.addFlashAttribute("userInfo", form)
        return view.redirect(form)
    }
}
