import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput
import com.gargoylesoftware.htmlunit.html.HtmlTextInput
import org.slf4j.LoggerFactory
import java.io.IOException

class LoginPage(webClient: WebClient) : Page(webClient) {
    private val log = LoggerFactory.getLogger(javaClass)
    private lateinit var page: HtmlPage
    fun navigateToPage(): LoginPage {
        try {
            page = webClient.getPage("file:${AUT_PATH}login.html")
        } catch (e: IOException) {
            log.error("An error occured on navigateToPage.", e)
        }
        return this
    }

    override val isAt: Boolean
        get() = "Login" == page.titleText

    fun enterUsername(username: String?): LoginPage {
        (page.getElementById("username") as HtmlTextInput).text = username
        return this
    }

    fun enterPassword(password: String?): LoginPage {
        (page.getElementById("password") as HtmlPasswordInput).text = password
        return this
    }

    fun login(): AlbumListPage {
        try {
            page.getElementById("loginButton").click()
        } catch (e: IOException) {
            log.error("An error occured on login.", e)
        }
        return AlbumListPage(webClient)
    }
}