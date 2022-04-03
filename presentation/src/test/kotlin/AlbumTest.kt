import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AlbumTest {
    @Test
    fun `set title`() = assertEquals("b", Album("a", "b", false, "").apply { title = "b" }.title)

    @Test
    fun `set artist`() = assertEquals("c", Album("a", "b", false, "").apply { artist = "c" }.artist)

    @Test
    fun `set classical`() = assertTrue(Album("a", "b", false, "").apply { isClassical = true }.isClassical)

    @Test
    fun testSetComposer() = assertEquals("w", Album("a", "b", false, "").apply {
        isClassical = true
        composer = "w"
    }.composer)
}