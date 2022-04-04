import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class AdvancedSorceressTest {
    @Test
    fun `smoke everything`() = listOf(OldTobyTobacco(), RivendellTobacco(), SecondBreakfastTobacco()).forEach {
        assertLogContains("AdvancedSorceress smoking ${it::class.simpleName}") {
            AdvancedSorceress().apply { setTobacco(it) }.smoke()
        }
    }
}