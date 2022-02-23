package view

import action.Content
import org.mockito.kotlin.*
import store.ContentStore
import kotlin.test.Test

class ContentViewTest {
    @Test
    fun `test storeChanged`() {
        val store = mock<ContentStore>()
        whenever(store.content).thenReturn(Content.PRODUCTS)
        ContentView().storeChanged(store)
        verify(store, times(1)).content
        verifyNoMoreInteractions(store)
    }
}