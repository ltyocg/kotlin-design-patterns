import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.*
import org.slf4j.LoggerFactory
import java.io.IOException

class AlbumPage(webClient: WebClient) : Page(webClient) {
    private val log = LoggerFactory.getLogger(javaClass)
    private lateinit var page: HtmlPage
    fun navigateToPage(): AlbumPage {
        try {
            page = webClient.getPage("file:${AUT_PATH}album-page.html")
        } catch (e: IOException) {
            log.error("An error occured on navigateToPage.", e)
        }
        return this
    }

    override val isAt: Boolean
        get() = "Album Page" == page.titleText

    fun changeAlbumTitle(albumTitle: String?): AlbumPage {
        (page.getElementById("albumTitle") as HtmlTextInput).text = albumTitle
        return this
    }

    fun changeArtist(artist: String?): AlbumPage {
        (page.getElementById("albumArtist") as HtmlTextInput).text = artist
        return this
    }

    fun changeAlbumYear(year: Int): AlbumPage {
        val albumYearSelectOption = page.getElementById("albumYear") as HtmlSelect
        albumYearSelectOption.setSelectedAttribute<com.gargoylesoftware.htmlunit.Page>(albumYearSelectOption.getOptionByValue(year.toString()), true)
        return this
    }

    fun changeAlbumRating(albumRating: String?): AlbumPage {
        val albumRatingInputTextField: HtmlTextInput = page.getElementById("albumRating") as HtmlTextInput
        albumRatingInputTextField.setText(albumRating)
        return this
    }

    fun changeNumberOfSongs(numberOfSongs: Int): AlbumPage {
        (page.getElementById("numberOfSongs") as HtmlNumberInput).text = numberOfSongs.toString()
        return this
    }

    fun cancelChanges(): AlbumListPage {
        try {
            (page.getElementById("cancelButton") as HtmlSubmitInput).click()
        } catch (e: IOException) {
            log.error("An error occured on cancelChanges.", e)
        }
        return AlbumListPage(webClient)
    }

    fun saveChanges(): AlbumPage {
        try {
            (page.getElementById("saveButton") as HtmlSubmitInput).click()
        } catch (e: IOException) {
            log.error("An error occured on saveChanges.", e)
        }
        return this
    }
}