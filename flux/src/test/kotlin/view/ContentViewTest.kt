package view

import ContentStore
import ContentView
import action.Content
import org.mockito.kotlin.*
import kotlin.test.Test

class ContentViewTest {
    @Test
    fun storeChanged() {
        val store = mock<ContentStore>()
        whenever(store.content).thenReturn(Content.PRODUCTS)
        ContentView().storeChanged(store)
        verify(store, times(1)).content
        verifyNoMoreInteractions(store)
    }
}
