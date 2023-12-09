import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlAnchor
import com.gargoylesoftware.htmlunit.html.HtmlPage
import io.github.oshai.kotlinlogging.KotlinLogging
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
                    it.click<com.gargoylesoftware.htmlunit.Page>()
                    return AlbumPage(webClient)
                } catch (e: IOException) {
                    logger.error(e) { "An error occured on selectAlbum" }
                }
            }
        throw IllegalArgumentException("No links with the album title: $albumTitle")
    }
}
