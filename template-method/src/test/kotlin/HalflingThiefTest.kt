import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

class HalflingThiefTest {
    @Test
    fun steal() {
        val method = mock<StealingMethod>()
        HalflingThief(method).steal()
        verify(method).steal()
        verifyNoMoreInteractions(method)
    }

    @Test
    fun changeMethod() {
        val initialMethod = mock<StealingMethod>()
        val thief = HalflingThief(initialMethod)
        thief.steal()
        verify(initialMethod).steal()
        val newMethod = mock<StealingMethod>()
        thief.changeMethod(newMethod)
        thief.steal()
        verify(newMethod).steal()
        verifyNoMoreInteractions(initialMethod, newMethod)
    }
}