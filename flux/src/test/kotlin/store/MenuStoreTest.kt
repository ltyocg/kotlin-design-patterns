package store

import action.Content
import action.ContentAction
import action.MenuAction
import action.MenuItem
import org.mockito.kotlin.*
import view.View
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