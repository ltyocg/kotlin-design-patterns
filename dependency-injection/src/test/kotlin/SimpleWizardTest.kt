import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleWizardTest {
    @Test
    fun smoke() {
        val assertListAppender = assertListAppender(OldTobyTobacco::class)
        SimpleWizard().smoke()
        assertEquals("SimpleWizard smoking OldTobyTobacco", assertListAppender.lastMessage())
    }
}