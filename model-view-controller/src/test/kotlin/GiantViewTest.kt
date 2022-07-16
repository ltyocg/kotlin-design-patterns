import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import org.mockito.kotlin.mock
import kotlin.test.Test
import kotlin.test.assertEquals

class GiantViewTest {
    @Test
    fun `display giant`() {
        val model = mock<GiantModel>()
        val assertListAppender = assertListAppender(GiantView::class)
        GiantView().displayGiant(model)
        assertEquals(model.toString(), assertListAppender.lastMessage())
    }
}