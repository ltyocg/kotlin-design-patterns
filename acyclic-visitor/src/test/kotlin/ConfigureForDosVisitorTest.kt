import ch.qos.logback.classic.Level
import ch.qos.logback.classic.filter.LevelFilter
import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertContains


class ConfigureForDosVisitorTest {
    @Test
    fun `visit for zoom`() {
        val zoom = Zoom()
        val assertListAppender = assertListAppender(ConfigureForDosVisitor::class).apply {
            addFilter(LevelFilter().apply { setLevel(Level.INFO) })
        }
        ConfigureForDosVisitor().visit(zoom)
        assertContains(
            assertListAppender.formattedList(),
            "$zoom used with Dos configurator."
        )
    }

    @Test
    fun `visit for hayes`() {
        val hayes = Hayes()
        val assertListAppender = assertListAppender(ConfigureForDosVisitor::class).apply {
            addFilter(LevelFilter().apply { setLevel(Level.INFO) })
        }
        ConfigureForDosVisitor().visit(hayes)
        assertContains(
            assertListAppender.formattedList(),
            "$hayes used with Dos configurator."
        )
    }
}

