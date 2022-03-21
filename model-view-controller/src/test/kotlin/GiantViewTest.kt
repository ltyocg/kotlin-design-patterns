import com.ltyocg.commons.assertLogContains
import org.mockito.kotlin.mock
import kotlin.test.Test

class GiantViewTest {
    @Test
    fun `display giant`() {
        val model = mock<GiantModel>()
        val view = GiantView()
        assertLogContains(model.toString()) { view.displayGiant(model) }
    }
}