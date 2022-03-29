import com.gargoylesoftware.htmlunit.WebClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoginPageTest {
    private val loginPage = LoginPage(WebClient())

    @BeforeEach
    fun setUp() {
        loginPage.navigateToPage()
    }

    @Test
    fun testLogin() {
        val albumListPage = loginPage
            .enterUsername("admin")
            .enterPassword("password")
            .login()
        albumListPage.navigateToPage()
        Assertions.assertTrue(albumListPage.isAt)
    }
}