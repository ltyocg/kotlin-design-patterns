import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

class ConfigureForUnixVisitorTest {
    @Test
    fun `test visit for zoom`() {
        val zoom = Zoom()
        assertLogContains(Level.INFO, "$zoom used with Unix configurator.") {
            ConfigureForUnixVisitor().visit(zoom)
        }
    }
}