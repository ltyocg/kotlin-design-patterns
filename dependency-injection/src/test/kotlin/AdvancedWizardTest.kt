import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class AdvancedWizardTest {
    @Test
    fun `smoke everything`() = listOf(OldTobyTobacco(), RivendellTobacco(), SecondBreakfastTobacco()).forEach {
        assertLogContains("AdvancedWizard smoking ${it::class.simpleName}") {
            AdvancedWizard(it).smoke()
        }
    }
}