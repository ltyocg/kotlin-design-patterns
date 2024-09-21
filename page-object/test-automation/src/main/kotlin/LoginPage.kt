import io.github.oshai.kotlinlogging.KotlinLogging
import org.htmlunit.WebClient
import org.htmlunit.html.HtmlPage
import org.htmlunit.html.HtmlPasswordInput
import org.htmlunit.html.HtmlTextInput
import java.io.IOException

class LoginPage(webClient: WebClient) : Page(webClient) {
    private val logger = KotlinLogging.logger {}
    private lateinit var page: HtmlPage
    fun navigateToPage(): LoginPage = apply {
        try {
            page = webClient.getPage("file:${AUT_PATH}login.html")
        } catch (e: IOException) {
            logger.error(e) { "An error occured on navigateToPage." }
        }
    }

    override val isAt: Boolean
        get() = "Login" == page.titleText

    fun enterUsername(username: String?): LoginPage = apply {
        (page.getElementById("username") as HtmlTextInput).text = username
    }

    fun enterPassword(password: String?): LoginPage = apply {
        (page.getElementById("password") as HtmlPasswordInput).text = password
    }

    fun login(): AlbumListPage {
        try {
            page.getElementById("loginButton").click<org.htmlunit.Page>()
        } catch (e: IOException) {
            logger.error(e) { "An error occured on login." }
        }
        return AlbumListPage(webClient)
    }
}
