import ch.qos.logback.classic.Level
import ch.qos.logback.classic.filter.LevelFilter
import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertContains

class ConfigureForUnixVisitorTest {
    @Test
    fun `visit for zoom`() {
        val zoom = Zoom()
        val assertListAppender = assertListAppender(ConfigureForUnixVisitor::class).apply {
            addFilter(LevelFilter().apply { setLevel(Level.INFO) })
        }
        ConfigureForUnixVisitor().visit(zoom)
        assertContains(
            assertListAppender.formattedList(),
            "$zoom used with Unix configurator."
        )
    }
}