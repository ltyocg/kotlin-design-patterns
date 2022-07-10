import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class AdvancedSorceressTest {
    @Test
    fun `smoke everything`() {
        val assertListAppender = assertListAppender {
            bind<Tobacco>(true)
        }
        listOf(OldTobyTobacco, RivendellTobacco, SecondBreakfastTobacco).forEach {
            AdvancedSorceress().apply { setTobacco(it) }.smoke()
            assertEquals("AdvancedSorceress smoking ${it::class.simpleName}", assertListAppender.lastMessage())
        }
    }
}