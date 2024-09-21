import io.github.oshai.kotlinlogging.KotlinLogging
import org.htmlunit.WebClient
import org.htmlunit.html.HtmlAnchor
import org.htmlunit.html.HtmlPage
import java.io.IOException

class AlbumListPage(webClient: WebClient) : Page(webClient) {
    private val logger = KotlinLogging.logger {}
    private lateinit var page: HtmlPage
    fun navigateToPage(): AlbumListPage = apply {
        try {
            page = webClient.getPage("file:${AUT_PATH}album-list.html")
        } catch (e: IOException) {
            logger.error(e) { "An error occured on navigateToPage." }
        }
    }

    override val isAt: Boolean
        get() = "Album List" == page.titleText

    fun selectAlbum(albumTitle: String): AlbumPage {
        page.getByXPath<HtmlAnchor>("//tr[@class='album']//a")
            .asSequence()
            .filter { it.textContent == albumTitle }
            .forEach {
                try {
                    it.click<org.htmlunit.Page>()
                    return AlbumPage(webClient)
                } catch (e: IOException) {
                    logger.error(e) { "An error occured on selectAlbum" }
                }
            }
        throw IllegalArgumentException("No links with the album title: $albumTitle")
    }
}
