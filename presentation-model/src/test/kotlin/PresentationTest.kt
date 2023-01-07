import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PresentationTest {
    private val albumList = arrayOf("HQ", "The Rough Dancer and Cyclical Night", "The Black Light", "Symphony No.5")

    @Test
    fun `create albumList`() =
        assertEquals(albumList.contentToString(), PresentationModel(PresentationModel.albumDataSet()).albumList.contentToString())

    @Test
    fun `setSelectedAlbumNumber 1`() {
        val selectId = 2
        assertEquals(albumList[selectId - 1], PresentationModel(PresentationModel.albumDataSet()).apply {
            setSelectedAlbumNumber(selectId)
        }.title)
    }

    @Test
    fun `setSelectedAlbumNumber 2`() {
        val selectId = 4
        assertEquals(albumList[selectId - 1], PresentationModel(PresentationModel.albumDataSet()).apply {
            setSelectedAlbumNumber(selectId)
        }.title)
    }

    @Test
    fun `setTitle 1`() {
        val testTitle = "TestTile"
        assertEquals(testTitle, PresentationModel(PresentationModel.albumDataSet()).apply {
            title = testTitle
        }.title)
    }

    @Test
    fun testSetTitle_2() {
        val testTitle = ""
        assertEquals(testTitle, PresentationModel(PresentationModel.albumDataSet()).apply {
            title = testTitle
        }.title)
    }

    @Test
    fun `setArtist 1`() {
        val testArtist = "TestArtist"
        assertEquals(testArtist, PresentationModel(PresentationModel.albumDataSet()).apply {
            artist = testArtist
        }.artist)
    }

    @Test
    fun `setArtist 2`() {
        val testArtist = ""
        assertEquals(testArtist, PresentationModel(PresentationModel.albumDataSet()).apply {
            artist = testArtist
        }.artist)
    }

    @Test
    fun setIsClassical() = assertTrue(PresentationModel(PresentationModel.albumDataSet()).apply {
        isClassical = true
    }.isClassical)

    @Test
    fun `setComposer false`() {
        val testComposer = "TestComposer"
        assertEquals("", PresentationModel(PresentationModel.albumDataSet()).apply {
            isClassical = false
            composer = testComposer
        }.composer)
    }

    @Test
    fun `setComposer true`() {
        val testComposer = "TestComposer"
        assertEquals(testComposer, PresentationModel(PresentationModel.albumDataSet()).apply {
            isClassical = true
            composer = testComposer
        }.composer)
    }
}