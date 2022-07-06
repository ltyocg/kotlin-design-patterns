import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import com.ltyocg.commons.assertListAppender
import kotlin.test.Test
import kotlin.test.assertEquals

class MammothTest {
    @Test
    fun timePasses() {
        val mammoth = Mammoth()
        val assertListAppender = assertListAppender {
            bind<State>(true)
        }
        mammoth.observe()
        assertEquals("The mammoth is calm and peaceful.", assertListAppender.lastMessage)
        assertEquals(1, assertListAppender.list.size)
        mammoth.timePasses()
        assertEquals("The mammoth gets angry!", assertListAppender.lastMessage)
        assertEquals(2, assertListAppender.list.size)
        mammoth.observe()
        assertEquals("The mammoth is furious!", assertListAppender.lastMessage)
        assertEquals(3, assertListAppender.list.size)
        mammoth.timePasses()
        assertEquals("The mammoth calms down.", assertListAppender.lastMessage)
        assertEquals(4, assertListAppender.list.size)
        mammoth.observe()
        assertEquals("The mammoth is calm and peaceful.", assertListAppender.lastMessage)
        assertEquals(5, assertListAppender.list.size)
    }

    private val ListAppender<ILoggingEvent>.lastMessage: String?
        get() = list.lastOrNull()?.formattedMessage

    @Test
    fun `test toString`() = assertEquals("The mammoth", Mammoth().toString())
}