import com.ltyocg.commons.assertLogContains
import kotlin.test.Test


class ConfigureForDosVisitorTest {
    @Test
    fun `test visit for zoom`() {
        val zoom = Zoom()
        assertLogContains("$zoom used with Dos configurator.") {
            ConfigureForDosVisitor().visit(zoom)
        }
    }

    @Test
    fun `test visit for hayes`() {
        val hayes = Hayes()
        assertLogContains("$hayes used with Dos configurator.") {
            ConfigureForDosVisitor().visit(hayes)
        }
    }
}

