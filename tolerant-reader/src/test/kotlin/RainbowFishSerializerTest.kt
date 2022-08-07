import RainbowFishSerializer.readV1
import RainbowFishSerializer.writeV1
import RainbowFishSerializer.writeV2
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path
import kotlin.test.*

class RainbowFishSerializerTest {
    @TempDir
    private lateinit var testFolder: Path
    private val v1 = RainbowFish("version1", 1, 2, 3)
    private val v2 = RainbowFishV2("version2", 4, 5, 6, sleeping = true, hungry = false, angry = true)

    @BeforeTest
    fun beforeEach() = assertTrue(Files.isDirectory(testFolder))

    @Test
    fun `writeV1 readV1`() {
        val outputPath = Files.createFile(testFolder.resolve("outputFile"))
        writeV1(v1, outputPath.toString())
        val fish = readV1(outputPath.toString())
        assertNotSame(v1, fish)
        assertEquals(v1.name, fish.name)
        assertEquals(v1.age, fish.age)
        assertEquals(v1.lengthMeters, fish.lengthMeters)
        assertEquals(v1.weightTons, fish.weightTons)
    }

    @Test
    fun `writeV2 readV1`() {
        val outputPath = Files.createFile(testFolder.resolve("outputFile2"))
        writeV2(v2, outputPath.toString())
        val fish = readV1(outputPath.toString())
        assertNotSame(v2, fish)
        assertEquals(v2.name, fish.name)
        assertEquals(v2.age, fish.age)
        assertEquals(v2.lengthMeters, fish.lengthMeters)
        assertEquals(v2.weightTons, fish.weightTons)
    }
}