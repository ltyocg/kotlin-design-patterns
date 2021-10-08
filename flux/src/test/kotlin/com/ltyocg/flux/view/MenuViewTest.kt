package com.ltyocg.flux.view

import com.ltyocg.flux.action.MenuItem
import com.ltyocg.flux.dispatcher.Dispatcher
import com.ltyocg.flux.store.MenuStore
import com.ltyocg.flux.store.Store
import org.mockito.kotlin.*
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