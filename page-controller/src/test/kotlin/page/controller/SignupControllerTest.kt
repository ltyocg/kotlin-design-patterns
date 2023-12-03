package page.controller

import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap
import kotlin.test.Test
import kotlin.test.assertEquals

class SignupControllerTest {
    @Test
    fun signup() {
        val controller = SignupController().apply { getSignup() }
        assertEquals(
            "redirect:/user",
            controller.create(SignupModel("Lily", "lily@email.com", "password1234"), RedirectAttributesModelMap())
        )
    }
}
