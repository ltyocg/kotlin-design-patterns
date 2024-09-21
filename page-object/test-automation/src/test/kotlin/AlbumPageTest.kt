import org.htmlunit.WebClient
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AlbumPageTest {
    private val albumPage = AlbumPage(WebClient())

    @BeforeTest
    fun setUp() {
        albumPage.navigateToPage()
    }

    @Test
    fun `save album`() = assertTrue(
        albumPage
            .changeAlbumTitle("25")
            .changeArtist("Adele Laurie Blue Adkins")
            .changeAlbumYear(2015)
            .changeAlbumRating("B")
            .changeNumberOfSongs(20)
            .saveChanges().isAt
    )

    @Test
    fun `cancel changes`() = assertTrue(albumPage.cancelChanges().navigateToPage().isAt)
}
