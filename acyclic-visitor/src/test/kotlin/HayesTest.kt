import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class HayesTest {
    @Test
    fun `test accept for dos`() {
        val hayes = Hayes()
        val mockVisitor = mock<ConfigureForDosVisitor>()
        hayes.accept(mockVisitor)
        verify(mockVisitor as HayesVisitor).visit(eq(hayes))
    }

    @Test
    fun `test accept for unix`() {
        val mockVisitor = mock<ConfigureForUnixVisitor>()
        Hayes().accept(mockVisitor)
        verifyNoMoreInteractions(mockVisitor)
    }
}