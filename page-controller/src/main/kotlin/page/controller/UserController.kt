package page.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserController {
    private val view = UserView()

    @GetMapping("user")
    fun getUserPath(form: UserModel, model: Model): String {
        model.addAttribute("name", form.name)
        model.addAttribute("email", form.email)
        return view.display(form)
    }
}
