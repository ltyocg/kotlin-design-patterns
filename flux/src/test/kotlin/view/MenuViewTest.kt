package view

import action.MenuItem
import dispatcher.Dispatcher
import org.mockito.kotlin.*
import store.MenuStore
import store.Store
import kotlin.test.Test

class MenuViewTest {
    @Test
    fun `test storeChanged`() {
        val store = mock<MenuStore>()
        whenever(store.selected).thenReturn(MenuItem.HOME)
        MenuView().storeChanged(store)
        verify(store, times(1)).selected
        verifyNoMoreInteractions(store)
    }

    @Test
    fun `test itemClicked`() {
        val store = mock<Store>()
        Dispatcher.registerStore(store)
        MenuView().itemClicked(MenuItem.PRODUCTS)
        verify(store, times(2)).onAction(any())
    }
}