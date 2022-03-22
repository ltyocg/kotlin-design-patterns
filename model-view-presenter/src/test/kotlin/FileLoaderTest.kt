import kotlin.test.Test
import kotlin.test.assertNull

class FileLoaderTest {
    @Test
    fun loadData() {
        assertNull(FileLoader().apply { fileName = "non-existing-file" }.loadData())
    }
}