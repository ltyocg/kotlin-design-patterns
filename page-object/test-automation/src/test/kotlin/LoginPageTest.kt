import com.gargoylesoftware.htmlunit.WebClient
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class LoginPageTest {
    private val loginPage = LoginPage(WebClient())

    @BeforeEach
    fun setUp() {
        loginPage.navigateToPage()
    }

    @Test
    fun login() = assertTrue(
        loginPage
            .enterUsername("admin")
            .enterPassword("password")
            .login()
            .navigateToPage()
            .isAt
    )
}