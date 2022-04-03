import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import kotlin.test.Test

class ZoomTest {
    @Test
    fun `accept for dos`() {
        val zoom = Zoom()
        val mockVisitor = mock<ConfigureForDosVisitor>()
        zoom.accept(mockVisitor)
        verify(mockVisitor as ZoomVisitor).visit(eq(zoom))
    }

    @Test
    fun `accept for unix`() {
        val zoom = Zoom()
        val mockVisitor = mock<ConfigureForUnixVisitor>()
        zoom.accept(mockVisitor)
        verify(mockVisitor as ZoomVisitor).visit(eq(zoom))
    }
}