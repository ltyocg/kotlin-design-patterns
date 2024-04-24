package view

import Dispatcher
import MenuStore
import MenuView
import Store
import action.MenuItem
import org.mockito.kotlin.*
import kotlin.test.Test

class MenuViewTest {
    @Test
    fun storeChanged() {
        val store = mock<MenuStore>()
        whenever(store.selected).thenReturn(MenuItem.HOME)
        MenuView().storeChanged(store)
        verify(store, times(1)).selected
        verifyNoMoreInteractions(store)
    }

    @Test
    fun itemClicked() {
        val store = mock<Store>()
        Dispatcher.registerStore(store)
        MenuView().itemClicked(MenuItem.PRODUCTS)
        verify(store, times(2)).onAction(any())
    }
}
