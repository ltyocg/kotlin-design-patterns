import com.gargoylesoftware.htmlunit.WebClient
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AlbumListPageTest {
    private val albumListPage = AlbumListPage(WebClient())

    @BeforeTest
    fun setUp() {
        albumListPage.navigateToPage()
    }

    @Test
    fun selectAlbum() = assertTrue(albumListPage.selectAlbum("21").navigateToPage().isAt)
}