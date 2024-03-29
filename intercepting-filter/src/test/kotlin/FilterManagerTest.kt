import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterManagerTest {
    @Test
    fun `test filterRequest`() {
        assertEquals("RUNNING...", FilterManager().filterRequest(mock()))
        verifyNoInteractions(mock<Target>())
    }

    @Test
    fun `test addFilter`() {
        val target = mock<Target>()
        val filterManager = FilterManager()
        verifyNoInteractions(target)
        val filter = mock<Filter>()
        whenever(filter.execute(any())).thenReturn("filter")
        filterManager.addFilter(filter)
        val order = mock<Order>()
        assertEquals("filter", filterManager.filterRequest(order))
        verify(filter, times(1)).execute(any())
        verifyNoInteractions(target, order)
    }
}