import kotlin.test.Test
import kotlin.test.assertEquals

class DisplayedAlbumsTest {
    @Test
    fun `add true`() = assertEquals("composer", DisplayedAlbums().apply {
        addAlbums("title", "artist", true, "composer")
    }.albums[0].composer)

    @Test
    fun `add false`() = assertEquals("", DisplayedAlbums().apply {
        addAlbums("title", "artist", false, "composer")
    }.albums[0].composer)
}