package com.ltyocg.flux.store

import com.ltyocg.flux.action.Content
import com.ltyocg.flux.action.ContentAction
import com.ltyocg.flux.action.MenuAction
import com.ltyocg.flux.action.MenuItem
import com.ltyocg.flux.view.View
import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentStoreTest {
    @Test
    fun `test onAction`() {
        val contentStore = ContentStore()
        val view = mock<View>()
        contentStore.registerView(view)
        verifyZeroInteractions(view)
        contentStore.onAction(MenuAction(MenuItem.PRODUCTS))
        verifyZeroInteractions(view)
        contentStore.onAction(ContentAction(Content.COMPANY))
        verify(view, times(1)).storeChanged(eq(contentStore))
        verifyNoMoreInteractions(view)
        assertEquals(Content.COMPANY, contentStore.content)
    }
}