import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test
import kotlin.test.assertEquals

class StatueTest {
    companion object {
        private lateinit var statue: Statue

        @JvmStatic
        @BeforeAll
        fun setup() {
            statue = Statue(1, 20)
        }
    }

    @Test
    fun `update for pending shoot`() {
        statue.frames = 10
        statue.update()
        assertEquals(11, statue.frames)
    }

    @Test
    fun `update for shooting`() {
        statue.frames = 19
        statue.update()
        assertEquals(0, statue.frames)
    }
}