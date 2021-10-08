package com.ltyocg.flux.view

import com.ltyocg.flux.action.Content
import com.ltyocg.flux.store.ContentStore
import org.mockito.kotlin.*
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