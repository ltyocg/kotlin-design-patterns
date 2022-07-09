import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertEquals

class AdvancedWizardTest {
    @Test
    fun `smoke everything`() {
        val assertListAppender = assertListAppender {
            bind<Tobacco>(true)
        }
        listOf(OldTobyTobacco, RivendellTobacco, SecondBreakfastTobacco).forEach {
            AdvancedWizard(it).smoke()
            assertEquals("AdvancedWizard smoking ${it::class.simpleName}", assertListAppender.formattedList().lastOrNull())
        }
    }
}