import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class SimpleWizardTest {
    @Test
    fun smoke() = assertLogContains("SimpleWizard smoking OldTobyTobacco") {
        SimpleWizard().smoke()
    }
}