package store

import MenuStore
import View
import action.Content
import action.ContentAction
import action.MenuAction
import action.MenuItem
import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MenuStoreTest {
    @Test
    fun onAction() {
        val menuStore = MenuStore()
        val view = mock<View>()
        menuStore.registerView(view)
        verifyNoMoreInteractions(view)
        menuStore.onAction(ContentAction(Content.COMPANY))
        verifyNoMoreInteractions(view)
        menuStore.onAction(MenuAction(MenuItem.PRODUCTS))
        verify(view, times(1)).storeChanged(eq(menuStore))
        verifyNoMoreInteractions(view)
        assertEquals(MenuItem.PRODUCTS, menuStore.selected)
    }
}
