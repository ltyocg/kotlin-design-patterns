import com.gargoylesoftware.htmlunit.WebClient

abstract class Page(protected val webClient: WebClient) {
    abstract val isAt: Boolean

    companion object {
        const val AUT_PATH = "../sample-application/src/main/resources/sample-ui/"
    }
}