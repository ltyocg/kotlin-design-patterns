import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KProperty

class PresentationModel(private val data: DisplayedAlbums) {
    private val logger = KotlinLogging.logger {}
    private var selectedAlbumNumber = 1
    private var selectedAlbum = data.albums[0]
    fun setSelectedAlbumNumber(albumNumber: Int) {
        logger.info { "Change select number from $selectedAlbumNumber to $albumNumber" }
        selectedAlbumNumber = albumNumber
        selectedAlbum = data.albums[selectedAlbumNumber - 1]
    }

    var title by ChangeLog(Album::title)
    var artist by ChangeLog(Album::artist)
    var isClassical by ChangeLog(Album::isClassical)
    var composer: String?
        get() = if (selectedAlbum.isClassical) selectedAlbum.composer else ""
        set(value) = if (selectedAlbum.isClassical) {
            logger.info { "Change album composer from ${selectedAlbum.composer} to $value" }
            selectedAlbum.composer = value
        } else logger.info { "Composer can not be changed" }
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
            logger.info { "Change album ${p.name} from ${p.get(selectedAlbum)} to $value" }
            p.set(selectedAlbum, value)
        }
    }
}
