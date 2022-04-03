import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ViewTest {
    @Test
    fun `save set artist and title`() {
        val testTitle = "testTitle"
        val testArtist = "testArtist"
        val view = View().apply {
            createView()
            txtArtist.text = testArtist
            txtTitle.text = testTitle
            saveToPMod()
            loadFromPMod()
        }
        assertEquals(testTitle, view.model.title)
        assertEquals(testArtist, view.model.artist)
    }

    @Test
    fun `save set classical and composer`() {
        val testComposer = "testComposer"
        val view = View().apply {
            createView()
            chkClassical.isSelected = true
            txtComposer.text = testComposer
            saveToPMod()
            loadFromPMod()
        }
        assertTrue(view.model.isClassical)
        assertEquals(testComposer, view.model.composer)
    }

    @Test
    fun `load 1`() = assertEquals("The Rough Dancer and Cyclical Night", View().apply {
        createView()
        model.setSelectedAlbumNumber(2)
        loadFromPMod()
    }.model.title)

    @Test
    fun `load 2`() = assertEquals("Symphony No.5", View().apply {
        createView()
        model.setSelectedAlbumNumber(4)
        loadFromPMod()
    }.model.title)
}