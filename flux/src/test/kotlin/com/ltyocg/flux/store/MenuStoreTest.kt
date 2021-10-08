package com.ltyocg.flux.store

import com.ltyocg.flux.action.Content
import com.ltyocg.flux.action.ContentAction
import com.ltyocg.flux.action.MenuAction
import com.ltyocg.flux.action.MenuItem
import com.ltyocg.flux.view.View
import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MenuStoreTest {
    @Test
    fun `test onAction`() {
        val menuStore = MenuStore()
        val view = mock<View>()
        menuStore.registerView(view)
        verifyZeroInteractions(view)
        menuStore.onAction(ContentAction(Content.COMPANY))
        verifyZeroInteractions(view)
        menuStore.onAction(MenuAction(MenuItem.PRODUCTS))
        verify(view, times(1)).storeChanged(eq(menuStore))
        verifyNoMoreInteractions(view)
        assertEquals(MenuItem.PRODUCTS, menuStore.selected)
    }
}