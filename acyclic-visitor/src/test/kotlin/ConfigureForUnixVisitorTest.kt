import com.ltyocg.commons.assertLogContains
import kotlin.test.Test

class ConfigureForUnixVisitorTest {
    @Test
    fun `test visit for zoom`() {
        val zoom = Zoom()
        assertLogContains("$zoom used with Unix configurator.") {
            ConfigureForUnixVisitor().visit(zoom)
        }
    }
}