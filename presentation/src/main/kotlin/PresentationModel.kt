import org.slf4j.LoggerFactory
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty

class PresentationModel(private val data: DisplayedAlbums) {
    private val log = LoggerFactory.getLogger(javaClass)
    private var selectedAlbumNumber = 1
    private var selectedAlbum = data.albums[0]
    fun setSelectedAlbumNumber(albumNumber: Int) {
        log.info("Change select number from {} to {}", selectedAlbumNumber, albumNumber)
        selectedAlbumNumber = albumNumber
        selectedAlbum = data.albums[selectedAlbumNumber - 1]
    }

    var title by ChangeLog(Album::title)
    var artist by ChangeLog(Album::artist)
    var isClassical by ChangeLog(Album::isClassical)
    var composer: String?
        get() = if (selectedAlbum.isClassical) selectedAlbum.composer else ""
        set(value) = if (selectedAlbum.isClassical) {
            log.info("Change album composer from {} to {}", selectedAlbum.composer, value)
            selectedAlbum.composer = value
        } else log.info("Composer can not be changed")
    val albumList: Array<String>
        get() = data.albums.map { it.title }.toTypedArray()

    companion object {
        fun albumDataSet(): DisplayedAlbums = DisplayedAlbums().apply {
            addAlbums("HQ", "Roy Harper", false, null)
            addAlbums("The Rough Dancer and Cyclical Night", "Astor Piazzola", false, null)
            addAlbums("The Black Light", "The Black Light", false, null)
            addAlbums("Symphony No.5", "CBSO", true, "Sibelius")
        }
    }

    private inner class ChangeLog<V>(private val p: KMutableProperty1<Album, V>) : ReadWriteProperty<PresentationModel, V> {
        override fun getValue(thisRef: PresentationModel, property: KProperty<*>): V = p.get(selectedAlbum)
        override fun setValue(thisRef: PresentationModel, property: KProperty<*>, value: V) {
            log.info("Change album {} from {} to {}", p.name, p.get(selectedAlbum), value)
            p.set(selectedAlbum, value)
        }
    }
}