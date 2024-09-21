import io.github.oshai.kotlinlogging.KotlinLogging
import org.htmlunit.WebClient
import org.htmlunit.html.*
import java.io.IOException

class AlbumPage(webClient: WebClient) : Page(webClient) {
    private val logger = KotlinLogging.logger {}
    private lateinit var page: HtmlPage
    fun navigateToPage(): AlbumPage = apply {
        try {
            page = webClient.getPage("file:${AUT_PATH}album-page.html")
        } catch (e: IOException) {
            logger.error(e) { "An error occured on navigateToPage." }
        }
    }

    override val isAt: Boolean
        get() = "Album Page" == page.titleText

    fun changeAlbumTitle(albumTitle: String?): AlbumPage = apply {
        (page.getElementById("albumTitle") as HtmlTextInput).text = albumTitle
    }

    fun changeArtist(artist: String?): AlbumPage = apply {
        (page.getElementById("albumArtist") as HtmlTextInput).text = artist
    }

    fun changeAlbumYear(year: Int): AlbumPage = apply {
        val albumYearSelectOption = page.getElementById("albumYear") as HtmlSelect
        albumYearSelectOption.setSelectedAttribute<org.htmlunit.Page>(albumYearSelectOption.getOptionByValue(year.toString()), true)
    }

    fun changeAlbumRating(albumRating: String?): AlbumPage = apply {
        val albumRatingInputTextField: HtmlTextInput = page.getElementById("albumRating") as HtmlTextInput
        albumRatingInputTextField.text = albumRating
    }

    fun changeNumberOfSongs(numberOfSongs: Int): AlbumPage = apply {
        (page.getElementById("numberOfSongs") as HtmlNumberInput).text = numberOfSongs.toString()
    }

    fun cancelChanges(): AlbumListPage {
        try {
            (page.getElementById("cancelButton") as HtmlSubmitInput).click<org.htmlunit.Page>()
        } catch (e: IOException) {
            logger.error(e) { "An error occured on cancelChanges." }
        }
        return AlbumListPage(webClient)
    }

    fun saveChanges(): AlbumPage = apply {
        try {
            (page.getElementById("saveButton") as HtmlSubmitInput).click<org.htmlunit.Page>()
        } catch (e: IOException) {
            logger.error(e) { "An error occured on saveChanges." }
        }
    }
}
