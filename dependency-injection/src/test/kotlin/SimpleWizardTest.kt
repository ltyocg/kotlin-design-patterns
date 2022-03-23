import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

class SimpleWizardTest {
    @Test
    fun `test smoke`() {
        assertLogContains(Level.INFO, "SimpleWizard smoking OldTobyTobacco") {
            SimpleWizard().smoke()
        }
    }
}